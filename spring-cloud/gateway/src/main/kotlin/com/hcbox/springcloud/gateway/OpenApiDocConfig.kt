package com.hcbox.springcloud.gateway

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.slf4j.LoggerFactory
import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.SwaggerUiConfigParameters
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.route.RouteDefinition
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Configuration
class OpenApiDocConfig {
    companion object {
        private val log = LoggerFactory.getLogger(OpenApiDocConfig::class.java)
    }

//    @Value("\${springdoc.version}")
//    var version: String? = null

    @Bean
    @Lazy(false)
    fun apis(
        swaggerUiConfigParameters: SwaggerUiConfigParameters, locator: RouteDefinitionLocator
    ): List<GroupedOpenApi> {
        val groups: List<GroupedOpenApi> = ArrayList()
        val definitions = locator.routeDefinitions.collectList().block()!!
        for (definition in definitions) {
            println("id: " + definition.id + "  " + definition.uri.toString())
        }
        definitions.stream()
            .filter { routeDefinition: RouteDefinition -> routeDefinition.id.matches(".*-service".toRegex()) }
            .forEach { routeDefinition: RouteDefinition ->
                val name = routeDefinition.id.replace("-service".toRegex(), "")
                swaggerUiConfigParameters.addGroup(name)
                log.info("name=$name")
                GroupedOpenApi.builder().pathsToMatch("/$name/**").group(name).build()
            }
        return groups
    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title("Gateway API")
//                    .version(version)
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }
}
