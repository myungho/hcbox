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

group = "com.hcbox.api"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

description = "hcbox-api"
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven(url = "https://packages.confluent.io/maven")
    maven(url = "https://repo.spring.io/plugins-release")
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation (group= "org.springdoc", name="springdoc-openapi-webflux-ui", version = "1.5.2")
    implementation("io.confluent:kafka-avro-serializer:5.3.0")
    implementation("com.sksamuel.avro4k:avro4k-core:0.41.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}
