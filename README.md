# Asteroids

Microservice to obtain the top 3 largest asteroids with risk of impact with the earth.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Java 11
* Maven 3.6

### Installation 

These are the operations permited by the project:

* General compilation

```bash
mvn clean install
```

* Skip unit tests

```bash
mvn clean install -DskipTests
```

* Run unit tests

```bash
mvn test
```

### Usage

#### Local

Run the application using the Maven Plugin:

```bash
mvn spring-boot:run
```

Run the application with java jar:

```bash
java -jar target/
```
