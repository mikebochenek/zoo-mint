# Zoo Mint

Zoo Mint is a Java 17 Maven project with a Spring Boot web application. The
application starts an embedded web server and provides a status API.

## Run the application

From the project root, start the application with Maven:

```sh
mvn spring-boot:run
```

The server listens on [http://localhost:8088](http://localhost:8088).

## Status API

`GET http://localhost:8088/api/status` returns the application start time, Java
version, and JVM heap memory values in bytes. For example:

```json
{
  "serverStartTime": "2026-10-11T06:00:00Z",
  "javaVersion": "17.0.14",
  "freeMemoryBytes": 123456789,
  "totalMemoryBytes": 234881024,
  "maxMemoryBytes": 4294967296
}
```

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
