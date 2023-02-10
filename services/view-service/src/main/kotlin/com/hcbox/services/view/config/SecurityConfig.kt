package com.hcbox.services.view.config

import org.springframework.context.annotation.Bean
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import reactor.core.publisher.Mono


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {
    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity,
        jwtAuthenticationConverter: Converter<Jwt?, Mono<AbstractAuthenticationToken?>?>?
    ): SecurityWebFilterChain {

        http.authorizeExchange { authorizeExchangeSpec ->
            authorizeExchangeSpec.pathMatchers("/webjars/**", "/v3/api-docs/**", "swagger*/**")
                .permitAll()
                .anyExchange().authenticated()
        }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter)
        return http.build()
    }

}
