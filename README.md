Spring Cloud ECS Example
-
This is an example project to demonstrate the usage of Spring Cloud + ECS to implement
microservices.

# Architecture

This project is based on the Spring Cloud base architecture (https://spring.io/cloud).

![Alt text](architecture.svg)

# Building Docker Images

To build docker images you can run `mvn -P docker-build clean package`.

Make sure you have a Docker Desktop or alternative running.

### MacOS
In case you're using MacOS with Colima, run the following in your terminal and then restart IntelliJ
```
launchctl setenv DOCKER_HOST unix://${HOME}/.colima/docker.sock
```
### How to run using Docker

* Make sure you have Docker and Docker Compose installed
* Just run `docker-compose up`

# Building Java Application only

A simple maven build will do `mvn clean package`.

### How to run using an IDE

* Just start the services by running the `main` method in the `*Application.java` classes in the following order: 
    * config-server
    * eureka
    * others

# Components
* Spring Cloud Configuration Server (config-server): serves configuration to all services
based on hierarchical and profile based YAML configuration files stored in a Git repository

  http://localhost:8888/anyservicename/anyprofile

* Eureka (eureka): provides service registry and discovery

  http://localhost:8761

* Hello Service (hello-service): example greeting service

  http://localhost:9800/docs

* Weather Service (weather-service): example weather service connecting to a third party API using RestTemplate

  http://localhost:9810/docs

* Spring Boot Admin (spring-boot-admin): tools to verify current state individual microservice
  instances, change logging levels, etc

  http://localhost:9001

* Spring Cloud Gateway: exposes multiple microservices for external usage, provides
  swagger documentation
  
  http://localhost:9090/docs


