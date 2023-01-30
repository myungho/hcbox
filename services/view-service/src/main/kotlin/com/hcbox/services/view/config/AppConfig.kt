package com.hcbox.services.view.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("app")
data class AppConfig(
    val service: ServiceConfig,
) {

    data class ServiceConfig(
        val order: ClientConfig,
        val product: ClientConfig,
    )

    data class ClientConfig(
        val baseUrl: String? = null
    )
}
