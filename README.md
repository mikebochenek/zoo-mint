# Zoo Mint

Zoo Mint is a Java 17 Maven project with a Spring Boot web application. The
application starts an embedded web server and provides a status API.

## Run the application

From the project root, start the application with Maven:

```sh
mvn spring-boot:run
```

The server listens on [http://localhost:8088](http://localhost:8088).

## Image upload API

`POST http://localhost:8088/api/images` accepts a `multipart/form-data` request
with an `image` file part. Valid JPEG, PNG, GIF, BMP, and WBMP images up to 10 MB
are saved under `uploads/images` by default, using a generated filename rather
than the client-provided filename. The response contains the saved filename,
detected content type, and size:

```json
{
  "filename": "b6c78276-02f9-4b9c-a04d-ff1ce5c00dae.png",
  "contentType": "image/png",
  "sizeBytes": 68
}
```

The storage directory can be changed with the `app.image-storage.location`
property. For example, set it to an absolute path to keep uploaded images
outside the application directory. The multipart file and request limits can
be adjusted with `spring.servlet.multipart.max-file-size` and
`spring.servlet.multipart.max-request-size`.

An Android client using OkHttp can upload a file as follows. Add the
`android.permission.INTERNET` permission to the app manifest, and replace
`<server-host>` with an address reachable from the device (not `localhost`).
Use HTTPS when exposing the API beyond local development.

```kotlin
val requestBody = file.asRequestBody("image/jpeg".toMediaType())
val multipartBody = MultipartBody.Builder()
    .setType(MultipartBody.FORM)
    .addFormDataPart("image", file.name, requestBody)
    .build()
val request = Request.Builder()
    .url("http://<server-host>:8088/api/images")
    .post(multipartBody)
    .build()
```

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
