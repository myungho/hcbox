plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"

    base
    idea
    java

    kotlin("plugin.lombok") version "1.7.20"
    id("io.freefair.lombok") version "5.3.0"

    kotlin("jvm") version "1.7.20"
    kotlin("kapt") version "1.7.20"
    kotlin("plugin.spring") version "1.7.20"
    kotlin("plugin.jpa") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
}

description = "common"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

kapt {
    keepJavacAnnotationProcessors = true
}

kotlinLombok {
    lombokConfigurationFile(file("lombok.config"))
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
    annotation("org.apache.commons.dbcp")
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
    implementation("org.hibernate:hibernate-core:5.3.9.Final")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.3")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
    implementation("org.projectreactor:reactor-core:1.1.6.RELEASE")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.0")
    implementation("org.springframework.boot:spring-boot-autoconfigure-processor")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    compileOnly("org.projectlombok:lombok")

    annotationProcessor("org.projectlombok:lombok")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
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
