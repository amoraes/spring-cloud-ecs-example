Spring Cloud ECS Example
-
This is an example project to demonstrate the usage of Spring Cloud + ECS to implement
microservices.

#### Components
* Spring Cloud Configuration Server (config-server): serves configuration to all services
based on YAML configuration files stored in a Git repository
* Eureka (eureka): provides service registry and discovery
* Spring Boot Admin (spring-boot-admin): tools to verify current state individual microservice
instances, change logging levels, etc
* API Gateway with Zuul Proxy (gateway): exposes multiple microservices for external usage, provides
swagger documentation
* Hello Service (hello-service): example greeting service
* Weather Service (weather-service): example weather service connecting to a third party API using RestTemplate
* Microservice Autoconfiguration (microservice-autoconfiguration): base autoconfiguration library 
shared across services (logging, eureka, caching, etc)    