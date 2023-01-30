package com.hcbox.services.view

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
@ConfigurationPropertiesScan
@SpringBootApplication
class ViewApplication

fun main(args: Array<String>) {
    runApplication<ViewApplication>(*args)
}
