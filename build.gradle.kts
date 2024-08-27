import com.diffplug.spotless.LineEnding
import com.google.protobuf.gradle.id
import org.springframework.boot.gradle.tasks.run.BootRun
import java.nio.charset.StandardCharsets

plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.hibernate.orm") version "6.5.2.Final"
    id("io.freefair.lombok") version "8.6"
    id("com.diffplug.spotless") version "6.25.0"
    id("com.palantir.git-version") version "3.1.0"
    id("com.google.cloud.tools.jib") version "3.4.3"
    id("com.google.protobuf") version "0.9.4"
}

val gitVersion: groovy.lang.Closure<String> by extra
val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra

group = "ch.presentium"
version = gitVersion()

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("io.grpc:grpc-bom:1.66.0")
    }
}

dependencies {
    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

    // Monitoring
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
    implementation("software.amazon.jdbc:aws-advanced-jdbc-wrapper:2.3.8")
    runtimeOnly("org.postgresql:postgresql")

    // Vault
    implementation("org.springframework.vault:spring-vault-core:3.1.2")

    // gRPC
    implementation("io.grpc:grpc-netty-shaded")
    implementation("io.grpc:grpc-protobuf")
    implementation("io.grpc:grpc-services")
    implementation("io.grpc:grpc-stub")
    implementation("com.salesforce.servicelibs:reactor-grpc-stub:1.2.4")
    implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")

    // Utils
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // Devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.hibernate.orm:hibernate-ant")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("com.h2database:h2")

    // Test containers
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:vault")

    // Misc
    compileOnly("jakarta.annotation:jakarta.annotation-api:1.3.5")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }

    all {
        exclude(group = "junit", module = "junit")
    }
}

hibernate {
    enhancement {
        enableAssociationManagement = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<BootRun>("bootRun") {
    systemProperty("spring.profiles.active", "dev")
    classpath(configurations["developmentOnly"])
}

tasks.named<JavaCompile>("compileJava") {
    options.compilerArgs.addAll(
        listOf(
            "-Amapstruct.defaultComponentModel=spring",
        ),
    )
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.4"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java"
        }
        id("reactor") {
            artifact = "com.salesforce.servicelibs:reactor-grpc:1.2.4"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {}
                id("reactor") {}
            }
        }
    }
}

val details = versionDetails()

tasks.withType<ProcessResources> {
    filesMatching("build.properties") {
        expand(
            mapOf(
                "version" to gitVersion(),
                "details" to
                    mapOf(
                        "lastTag" to details.lastTag,
                        "commitDistance" to details.commitDistance,
                        "dirty" to details.isCleanTag,
                        "branchName" to details.branchName,
                        "gitHash" to details.gitHash,
                        "gitHashFull" to details.gitHashFull,
                    ),
            ),
        )
    }
}

jib {
    from {
        image = "eclipse-temurin:21.0.4_7-jre-alpine@sha256:3f716d52e4045433e94a28d029c93d3c23179822a5d40b1c82b63aedd67c5081"
    }
    to {
        image = "ghcr.io/presentium/api:${project.version}"
    }
}

spotless {
    val ref = project.properties["ratchetFrom"] as String?
    if (ref != null && ref.trim().isNotEmpty()) {
        ratchetFrom(ref.trim())
    }

    lineEndings = LineEnding.UNIX
    encoding = StandardCharsets.UTF_8

    format("misc") {
        target("**/*.{md,gitignore,properties}")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    java {
        target("**/*.java")
        targetExclude("build/generated/**/*")

        toggleOffOn()

        // Import sorting and cleaning
        importOrder()
        removeUnusedImports()

        // generic refactorings
        cleanthat()

        // Google prettier-like formatting
        prettier(
            mapOf(
                "prettier" to "3.3.3",
                "prettier-plugin-java" to "2.6.4",
            ),
        ).config(
            mapOf(
                "parser" to "java",
                "tabWidth" to 4,
                "printWidth" to 120,
                "plugins" to listOf("prettier-plugin-java"),
            ),
        )

        // Reorganize type annotations
        formatAnnotations()
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint()
    }
}

apply(from = rootProject.file("gradle/tasks/install-git-hooks.gradle.kts"))
