# camunda tutorial playground spring boot

The example is generated using the [Camunda Platform Initializr](https://start.camunda.com). It is intended to check your environment for development purposes and to provide a basic example to start from.

## Prerequierements

- git
- JDK >= 11
- [maven](https://maven.apache.org) (3.x.x) or use the include [maven-wrapper](https://github.com/takari/maven-wrapper) `mvnw` (keep in mind to replace `mvn` with `mvnw` on command line).
- IDE supporting java, maven and optionally spring boot (eclipse, intellij idea, visual studio code, spring tool suite, ...)
- [camunda Modeler](https://camunda.com/de/download/modeler/)

## Verify

Clone the repository and verify on command line:

```
mvn clean package
```

```
mvn spring-boot:run
```

Point your browser to http://localhost:8080 and play around (credentials `admin/admin`)

Also verify you can start the application (`java/com/example/workflow/Application.java`) from inside your IDE.

H2 console is enabled and located at http://localhost:8080/h2-console. 

camunda credentials and jdbc connection parameters are defined in [application.yaml](src/main/resources/application.yaml).
