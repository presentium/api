# Presentium - Backend API

This is the backend API for Presentium, handling connections with the databse, the reader devices and frontend instances.
It is built with [Spring Boot](https://spring.io/projects/spring-boot) in Java 21.

## Stack

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Hibernate](https://hibernate.org/)
- [Liquibase Migrations](https://www.liquibase.com/)
- [Mapstruct](https://mapstruct.org/)

The project is built with [Gradle](https://gradle.org/).

## API Documentation

The API documentation is available at [api.presentium.ch/swagger-ui.html](https://api.presentium.ch/swagger-ui.html).
It is built automatically using [Springdoc](https://springdoc.org/) and available on the local instance as well.

## Development Server

Start the development server on `http://localhost:13000`:

```bash
./gradlew bootRun
```

## Continuous Delivery

Each commit on the `main` branch is deployed to [staging-api.presentium.ch](https://staging-api.presentium.ch)
using GitHub Actions, to a dedicated namespace on the Cloud infrastructure.

The default branch therefore is `main` and it is protected. When a change is ready to be deployed,
the deployment workflow should be triggered manually providing the desired version number.

## Contributing

Please refer to the [Contributing Guide][contributing] before making a pull request.

[contributing]: https://github.com/presentium/meta/blob/main/CONTRIBUTING.md
