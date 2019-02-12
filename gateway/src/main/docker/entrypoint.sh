#!/bin/sh
if test "${AWS_EXECUTION_ENV}" = 'AWS_ECS_EC2'; then
    # Get the real ip, port and management port (health check and metrics) from the host metadata
    export APPNAME=`curl ${ECS_CONTAINER_METADATA_URI} | jq -r '.Name'`
    export DOCKER_ID=`curl ${ECS_CONTAINER_METADATA_URI}/task | jq -r --arg APPNAME "$APPNAME" '.Containers[] | select(.Name == $APPNAME) | .DockerId'`
    export HOST_PORT=`curl http://172.17.0.1:51678/v1/tasks | jq -r --arg DOCKER_ID "$DOCKER_ID" '.Tasks[].Containers[] | select(.DockerId == $DOCKER_ID) | .Ports[] | select(.ContainerPort == 8080) | .HostPort'`
    export HOST_IP=`curl http://169.254.169.254/latest/meta-data/local-ipv4`
fi
if test "${LOCAL_DOCKER_ENV}" = 'true'; then
    echo `getent hosts host.docker.internal | awk '{ print $1 }'` ${HOST_DOMAIN} >> /etc/hosts
    # wait for config and eureka servers
    export CONFIG_HEALTH_CHECK=`env | grep spring.cloud.config.uri | cut -d '=' -f2`/actuator/health
    until $(curl --output /dev/null --silent --head --fail ${CONFIG_HEALTH_CHECK}); do
        sleep 3
    done
    export EUREKA_HEALTH_CHECK="http://${HOST_DOMAIN}:8761/actuator/health"
    until $(curl --output /dev/null --silent --head --fail ${EUREKA_HEALTH_CHECK}); do
        sleep 3
    done
fi
java $JAVA_OPTIONS -jar app.jar