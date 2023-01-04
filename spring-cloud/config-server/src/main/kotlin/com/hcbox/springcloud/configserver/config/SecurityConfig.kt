package com.hcbox.springcloud.configserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Throws(Exception::class)
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .anyRequest().permitAll()

        return http.build()
    }
}
