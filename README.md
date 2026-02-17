# Order Service

Event-driven Order microservice built with Spring Boot and RabbitMQ

## Features

- Create orders via REST API
- Persist orders in database
- Publish OrderCreated event to RabbitMQ exchange

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- RabbitMQ
- H2 Database
- Docker (RabbitMQ)

## Run locally

Start RabbitMQ:

docker run -d --hostname rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management

Start app:

./mvnw spring-boot:run

## Test

curl -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{"customerId":"customer1", "totalAmount":49.99}'

RabbitMQ UI:
http://localhost:15672

H2 Console
http://localhost:8080/h2-console