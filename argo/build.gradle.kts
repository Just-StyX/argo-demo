plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "jsl.group"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot and argo"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
    enabled = false
}

tasks.bootBuildImage {
    imageName = "${project.name}:${project.version}"
    environment = mapOf("BP_JVM_VERSION" to "24.*")
    docker {
        publishRegistry {
            url = project.findProperty("REGISTRY_URL") as? String?
            username = project.findProperty("REGISTRY_USERNAME") as? String?
            password = project.findProperty("registryToken") as? String?
        }
    }
}
