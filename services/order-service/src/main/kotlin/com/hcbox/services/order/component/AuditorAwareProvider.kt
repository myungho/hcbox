package com.hcbox.services.order.component

import org.slf4j.LoggerFactory
import org.springframework.data.domain.AuditorAware
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import reactor.core.publisher.Mono
import java.util.*

class AuditorAwareProvider : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        // todo fixme
        val authentication: Mono<Authentication>  = ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
        val name = authentication.block()?.name

        return Optional.of("UNKNOWN")
//        return authentication.block()!!
    }

    companion object {
        var logger = LoggerFactory.getLogger(AuditorAwareProvider::class.java)
    }
}
