# Zoo Mint

Zoo Mint is a Java 17 Maven project with a Spring Boot web application. The
application currently starts an embedded web server; API endpoints can be added
as the project grows.

## Run the application

From the project root, start the application with Maven:

```sh
mvn spring-boot:run
```

The server listens on [http://localhost:8088](http://localhost:8088). No API
endpoints are configured yet.

To build and run the executable JAR instead:

```sh
mvn package
java -jar target/zoo-mint-1.0-SNAPSHOT.jar
```

## Requirements

- JDK 17 or newer
- Maven 3.6.3 or newer

The existing Java sources remain under `src/zoo`; Maven is configured to compile
them from that source root. Application configuration is under
`src/main/resources`.
