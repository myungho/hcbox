package com.hcbox.common

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.hcbox.common"])
class CommonsAutoConfiguration {
    companion object {
        private val log = LoggerFactory.getLogger(CommonsAutoConfiguration::class.java)
    }

    init {
        log.info("CommonsAutoConfiguration Created")
    }
}
