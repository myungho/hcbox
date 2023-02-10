package com.hcbox.services.view.config

import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.util.ObjectUtils
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

@RequiredArgsConstructor
class KeycloakGrantedAuthoritiesConverter(clientId: String?) :
    Converter<Jwt, Collection<GrantedAuthority>> {


    private val defaultAuthoritiesConverter: Converter<Jwt, Collection<GrantedAuthority>> =
        JwtGrantedAuthoritiesConverter()
    private val clientId: String? = clientId

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val realmRoles = realmRoles(jwt)
        val clientRoles = clientRoles(jwt, clientId)
        val authorities: MutableCollection<GrantedAuthority> = Stream.concat(
            realmRoles!!.stream(), clientRoles.stream()
        )
            .map { role: String? -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toSet())
        authorities.addAll(defaultGrantedAuthorities(jwt))
        return authorities
    }

    private fun defaultGrantedAuthorities(jwt: Jwt): Collection<GrantedAuthority> {
        return Optional.ofNullable(defaultAuthoritiesConverter.convert(jwt))
            .orElse(emptySet())
    }

    fun realmRoles(jwt: Jwt): List<String>? {
        return Optional.ofNullable(jwt.getClaimAsMap(CLAIM_REALM_ACCESS))
            .map { realmAccess: Map<String?, Any?> -> realmAccess[ROLES] as List<String>? }
            .orElse(emptyList())
    }

    fun clientRoles(jwt: Jwt, clientId: String?): List<String> {
        return if (ObjectUtils.isEmpty(clientId)) {
            emptyList()
        } else Optional.ofNullable(jwt.getClaimAsMap(RESOURCE_ACCESS))
            .map { resourceAccess: Map<String?, Any?> -> resourceAccess[clientId] as Map<String?, List<String>>? }
            .map { clientAccess: Map<String?, List<String>>? -> clientAccess!![ROLES] }
            .orElse(emptyList())!!
    }

    companion object {
        private const val ROLES = "roles"
        private const val CLAIM_REALM_ACCESS = "realm_access"
        private const val RESOURCE_ACCESS = "resource_access"

        private val log = LoggerFactory.getLogger(KeycloakGrantedAuthoritiesConverter::class.java)
    }
}
