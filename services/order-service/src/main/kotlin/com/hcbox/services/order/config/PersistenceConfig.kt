package com.hcbox.services.order.config

import com.hcbox.services.order.component.AuditorAwareProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
class PersistenceConfig {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAwareProvider()
    }
}
