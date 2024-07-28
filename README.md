# Presentium - Backend API

This is the backend API for Presentium, handling connections with the databse, the reader devices and frontend
instances. It is built with [Spring Boot](https://spring.io/projects/spring-boot) in Java 21.

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

## Development

Start the development server on `http://localhost:13000`:

```bash
./gradlew bootRun
```

### Development Tips

#### Local environment

We leverage
[Spring Boot's Docker Compose module](https://docs.spring.io/spring-boot/reference/features/dev-services.html#features.dev-services.docker-compose)
to provision the local development environment. This includes a PostgreSQL database, a `dex` server acting as a OIDC
provider, and a Redis instance for session data and caching.

Running the API server in a development environment will automatically call `docker compose up` and add the necessary
connection details to the application context.

> [!TIP]
> When testing, either manually or in integration / end-to-end tests, you can use the `TestcontainersConfiguration` which
> provides the same environment as the local development setup, but it is volatile and is reset between each runs.
> 
> A Spring runner is also configured to use the Testcontainers configuration in `TestPresentiumApiApplication`.

#### Database migrations

When changing the database model, one should ensure that the changes are correctly defined in the Liquibase migrations.

Liquibase migrations shall always be placed under the app version they are being introduced. They can be found in the
`src/main/resources/db/changelog` directory.

Migration files are named with a prefix that corresponds to the GitHub Issue number that the task is related to,
followed by a short description of the task. For example, `GH-1_add-users-table.xml`.

Each version directory contains a local `_changelog.yaml` file that should be referenced in the main `changelog.yaml`
file.

> [!NOTE]
> We use `YAML` files for indexes as it is easier to follow when there is only simple references.
> For actual changelog files, please use the `XML` format, as it makes sure the changes can be correctly validated.

To help with creating changelog files, a unit test `DDLGeneratorTest` should be run once. It will generate the DDL in
`sql/ddl/create.sql`, which can be used as a reference for the new changelog file (for example by using diffs).

## Continuous Delivery

Each commit on the `main` branch is deployed to [staging-api.presentium.ch](https://staging-api.presentium.ch)
using GitHub Actions, to a dedicated namespace on the Cloud infrastructure.

The default branch therefore is `main` and it is protected. When a change is ready to be deployed,
the deployment workflow should be triggered manually providing the desired version number.

## Contributing

Please refer to the [Contributing Guide][contributing] before making a pull request.

[contributing]: https://github.com/presentium/meta/blob/main/CONTRIBUTING.md
