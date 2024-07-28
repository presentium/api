import com.diffplug.spotless.LineEnding
import java.nio.charset.StandardCharsets

plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.hibernate.orm") version "6.5.2.Final"
    id("org.graalvm.buildtools.native") version "0.10.2"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "ch.presentium"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
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

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.session:spring-session-data-redis")

    // Monitoring
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")

    // Utils
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.projectreactor:reactor-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Test containers
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("com.redis:testcontainers-redis:2.2.2")
    testImplementation("wf.garnier:testcontainers-dex:3.2.0")
}

hibernate {
    enhancement {
        enableAssociationManagement = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
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
        googleJavaFormat("1.22.0").reorderImports(false)

        // Reformat using 4 spaces
        indentWithTabs(2)
        indentWithSpaces(4)

        // Reorganize type annotations
        formatAnnotations()
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint()
    }
}

apply(from = rootProject.file("gradle/tasks/install-git-hooks.gradle.kts"))
