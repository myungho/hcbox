package com.hcbox.common.webclient

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class WebClientUtil(
    private val webClient: WebClient
) {
    fun <T> postBlock(returnClazz: Class<T>, baseUrl: String, uri: String, body: Any?): T? {
        return post(returnClazz, baseUrl, uri, body).block()
    }

    fun <T> postBlock(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        body: Any?,
        headers: HashMap<String?, String?>?
    ): T? {
        return post(returnClazz, baseUrl, uri, body, headers).block()
    }

    fun <T> post(returnClazz: Class<T>, baseUrl: String, uri: String, body: Any?): Mono<T> {
        return post(returnClazz, baseUrl, uri, body, null)
    }

    fun <T> post(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        body: Any?,
        headers: HashMap<String?, String?>?
    ): Mono<T> {
        return webClient
            .mutate()
            .baseUrl(baseUrl)
            .build()
            .post()
            .uri(uri)
            .headers { httpHeaders: HttpHeaders ->
                headers?.forEach { (headerName: String?, headerValue: String?) ->
                    if (headerName != null) {
                        httpHeaders.add(
                            headerName,
                            headerValue
                        )
                    }
                }
            }
            .bodyValue(body ?: "")
            .retrieve()
            .bodyToMono(returnClazz)
    }

    fun <T> putBlock(returnClazz: Class<T>, baseUrl: String, uri: String, body: Any?): T? {
        return put(returnClazz, baseUrl, uri, body).block()
    }

    fun <T> putBlock(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        body: Any?,
        headers: HashMap<String?, String?>?
    ): T? {
        return put(returnClazz, baseUrl, uri, body, headers).block()
    }

    fun <T> put(returnClazz: Class<T>, baseUrl: String, uri: String, body: Any?): Mono<T> {
        return put(returnClazz, baseUrl, uri, body, null)
    }

    fun <T> put(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        body: Any?,
        headers: HashMap<String?, String?>?
    ): Mono<T> {
        return webClient
            .mutate()
            .baseUrl(baseUrl)
            .build()
            .put()
            .uri(uri)
            .headers { httpHeaders: HttpHeaders ->
                headers?.forEach { (headerName: String?, headerValue: String?) ->
                    if (headerName != null) {
                        httpHeaders.add(
                            headerName,
                            headerValue
                        )
                    }
                }
            }
            .bodyValue(body ?: "")
            .retrieve()
            .bodyToMono(returnClazz)
    }

    fun <T> getBlock(returnClazz: Class<T>, baseUrl: String, uri: String): T? {
        return get(returnClazz, baseUrl, uri).block()
    }

    fun <T> getBlock(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        headers: HashMap<String?, String?>?
    ): T?{
        return get(returnClazz, baseUrl, uri, headers).block()
    }

    operator fun <T> get(returnClazz: Class<T>, baseUrl: String, uri: String): Mono<T> {
        return get(returnClazz, baseUrl, uri, null)
    }

    operator fun <T> get(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        headers: HashMap<String?, String?>?
    ): Mono<T> {
        return webClient
            .mutate()
            .baseUrl(baseUrl)
            .build()
            .get()
            .uri(uri)
            .headers { httpHeaders: HttpHeaders ->
                headers?.forEach { (headerName: String?, headerValue: String?) ->
                    if (headerName != null) {
                        httpHeaders.add(
                            headerName,
                            headerValue
                        )
                    }
                }
            }
            .retrieve()
            .bodyToMono(returnClazz)
    }

    fun <T> deleteBlock(returnClazz: Class<T>, baseUrl: String, uri: String): T? {
        return delete(returnClazz, baseUrl, uri).block()
    }

    fun <T> deleteBlock(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        headers: HashMap<String?, String?>?
    ): T? {
        return delete(returnClazz, baseUrl, uri, headers).block()
    }

    fun <T> delete(returnClazz: Class<T>, baseUrl: String, uri: String): Mono<T> {
        return delete(returnClazz, baseUrl, uri, null)
    }

    fun <T> delete(
        returnClazz: Class<T>,
        baseUrl: String,
        uri: String,
        headers: HashMap<String?, String?>?
    ): Mono<T> {
        return webClient
            .mutate()
            .baseUrl(baseUrl)
            .build()
            .delete()
            .uri(uri)
            .headers { httpHeaders: HttpHeaders ->
                headers?.forEach { (headerName: String?, headerValue: String?) ->
                    if (headerName != null) {
                        httpHeaders.add(
                            headerName,
                            headerValue
                        )
                    }
                }
            }
            .retrieve()
            .bodyToMono(returnClazz)
    }
}
