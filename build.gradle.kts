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
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.session:spring-session-data-redis")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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

    format("misc") {
        target("**/*.{md,gitignore,properties}")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    java {
        target("**/*.java")
        targetExclude("build/generated/**/*")

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
