import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"

    base
    idea
    java

    kotlin("jvm") version "1.7.20"
    kotlin("kapt") version "1.7.20"
    kotlin("plugin.spring") version "1.7.20"
    kotlin("plugin.jpa") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
    annotation("org.apache.commons.dbcp")
}

group = "com.hcbox.springcloud"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

description = "springcloud-config-server"
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
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

extra["springCloudVersion"] = "2021.0.3"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.bootJar {
    enabled = true
}
