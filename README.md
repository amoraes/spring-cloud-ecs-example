Spring Cloud ECS Example
-
This is an example project to demonstrate the usage of Spring Cloud + ECS to implement
microservices.

#### How to run using an IDE

* Just start the services by running the main classes in the following order: 
    * config-server
    * eureka
    * others
    
#### How to run using Docker

* Make sure you have Docker and Docker Compose installed
* Just run `docker-compose up`

###### Docker + IntelliJ (or any other IDE)
* Define an entry on your `/etc/hosts` (or `C:\Windows\System32\drivers\etc\hosts`) pointing `localhost.local.com` to `127.0.0.1`
* Stop the service you want to debug with `docker stop example-weather-service`
* When running your service on IntelliJ, set the following parameters:
`HOST_DOMAIN=localhost.local.com` and the profile as `docker` 

#### Components
* Spring Cloud Configuration Server (config-server): serves configuration to all services
based on YAML configuration files stored in a Git repository
`http://localhost:8888/anyservicename/anyprofile`

* Eureka (eureka): provides service registry and discovery
`http://localhost:8761`

* Spring Boot Admin (spring-boot-admin): tools to verify current state individual microservice
instances, change logging levels, etc
`http://localhost:9001`

* API Gateway with Zuul Proxy (gateway): exposes multiple microservices for external usage, provides
swagger documentation
`http://localhost:9090/swagger-ui.html`

* Hello Service (hello-service): example greeting service
`http://localhost:9800/greeting?name=Alessandro`

* Weather Service (weather-service): example weather service connecting to a third party API using RestTemplate
`http://localhost:9810/?location=Sydney`

* Microservice Autoconfiguration (microservice-autoconfiguration): base autoconfiguration library 
shared across services (logging, eureka, caching, etc)    