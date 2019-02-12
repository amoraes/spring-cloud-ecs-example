#!/bin/sh
if test "${AWS_EXECUTION_ENV}" = 'AWS_ECS_EC2'; then
    export HOST_IP=`curl $ECS_CONTAINER_METADATA_URI | jq -r '.Networks[0].IPv4Addresses[0]'`
fi
if test "${LOCAL_DOCKER_ENV}" = 'true'; then
    echo `getent hosts host.docker.internal | awk '{ print $1 }'` ${HOST_DOMAIN} >> /etc/hosts
    export CONFIG_HEALTH_CHECK=`env | grep spring.cloud.config.uri | cut -d '=' -f2`/actuator/health
    until $(curl --output /dev/null --silent --head --fail ${CONFIG_HEALTH_CHECK}); do
        sleep 3
    done
fi
java $JAVA_OPTIONS -jar app.jar