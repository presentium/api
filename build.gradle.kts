import com.diffplug.spotless.LineEnding
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

dependencies {
    // Spring starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
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

val details = versionDetails()

tasks.withType<ProcessResources> {
    filesMatching("build.properties") {
        expand(
            mapOf(
                "version" to project.version,
                "details" to
                    mapOf(
                        "lastTag" to details.lastTag,
                        "commitDistance" to details.commitDistance,
                        "dirty" to !details.isCleanTag,
                        "clean" to details.isCleanTag,
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
