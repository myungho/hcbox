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

group = "com.hcbox.services"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

description = "product-service"
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
    val querydslVersion = "5.0.0"

    implementation(project(":api"))
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.glassfish:jakarta.el:3.0.3")
    implementation("com.querydsl:querydsl-core:$querydslVersion")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.14")

    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.0.0")

    implementation("org.mapstruct:mapstruct:1.5.3.Final")

    implementation("io.confluent:kafka-avro-serializer:5.3.0")
    implementation("com.sksamuel.avro4k:avro4k-core:0.41.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    // local JAR
    implementation(fileTree(mapOf("dir" to "../libs", "include" to listOf( "*.jar"))))

    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    runtimeOnly("com.h2database:h2")

    kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

    testImplementation("junit:junit:4.13.2")
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
