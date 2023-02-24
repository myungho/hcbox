package com.hcbox.services.view.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hcbox.api.dto.ProductDto
import com.hcbox.api.dto.SchoolDto
import com.hcbox.api.dto.composite.products.CompositeProductDto
import com.hcbox.common.webclient.WebClientUtil
import com.hcbox.services.view.config.AppConfig
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CompositeProductService(
    private val appConfig: AppConfig,
    private val webClientUtil: WebClientUtil,
    private val objectMapper: ObjectMapper
) {
    fun retrieve(
        id: Long, gender: Int?, seasonType: Int?
    ): Mono<CompositeProductDto.CompositeProductReadDto> {

        return ReactiveSecurityContextHolder.getContext()
            .map { securityContext: SecurityContext ->
                securityContext.authentication.principal as Jwt
            }.flatMap {
                val headers = hashMapOf("Authorization" to "Bearer ${it.tokenValue}")

                val schoolDto = webClientUtil.get(
                    SchoolDto.SchoolReadDto::class.java,
                    appConfig.service.order.baseUrl!!,
                    "/schools/$id",
                    headers
                )

                val productListDto = webClientUtil.get(
                    Any::class.java,
                    appConfig.service.product.baseUrl!!,
                    "/product-mgmt/schools/$id/list?gender=${gender ?: ""}&seasonType=${seasonType ?: ""}",
                    headers
                ) as Mono<List<ProductDto.ProductReadDto>>

                Mono.zip(
                    { values: Array<Any?> ->
                        val productDtoList: List<ProductDto.ProductReadDto> =
                            objectMapper.convertValue(
                                values[1],
                                object : TypeReference<List<ProductDto.ProductReadDto>>() {})
                        createProductAggregate(
                            values[0] as SchoolDto.SchoolReadDto,
                            productDtoList
                        )
                    },
                    schoolDto,
                    productListDto
                )
            }.log()
    }

    private fun createProductAggregate(
        schoolReadDto: SchoolDto.SchoolReadDto, productReadDtos: List<ProductDto.ProductReadDto>
    ): CompositeProductDto.CompositeProductReadDto {
        var dto = CompositeProductDto.CompositeProductReadDto()
        dto.schoolDto = schoolReadDto
        dto.productDtoList = productReadDtos
        return dto
    }
}
