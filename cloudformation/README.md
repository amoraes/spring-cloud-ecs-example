How to deploy using AWS CloudFormation
-
1. Setup a VPC with two private subnets and a domain
1. Deploy the `microservice-ecs-cluster.yml`
1. Deploy the `internal-alb.yml`
1. Deploy the services: `config-server.yml`, `eureka.yml`, `spring-boot-admin.yml`, `gateway.yml`, `hello-service.yml` and `weather-service.yml`
