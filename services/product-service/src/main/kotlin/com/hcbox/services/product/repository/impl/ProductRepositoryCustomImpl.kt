package com.hcbox.services.product.repository.impl

import com.hcbox.api.dto.ProductDto
import com.hcbox.services.product.dao.ProductEntity
import com.hcbox.services.product.dao.QProductEntity
import com.hcbox.services.product.mapper.ProductMapper
import com.hcbox.services.product.repository.ProductRepositoryCustom
import com.querydsl.core.types.Predicate
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import java.util.stream.Collectors

class ProductRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val productMapper: ProductMapper
) :
    QuerydslRepositorySupport(
        ProductEntity::class.java
    ), ProductRepositoryCustom {
    private val qProductEntity = QProductEntity.productEntity

    override fun findAllByOptions(
        seasonType: Integer?,
        name: String?,
        pageable: PageRequest
    ): Page<ProductDto.ProductReadDto> {
        val content = jpaQueryFactory
            .selectFrom(qProductEntity)
            .where(eqSeasonType(seasonType), containsName(name))
            .offset(pageable!!.offset)
            .limit(pageable.pageSize.toLong())
            .fetch().stream().map { entity -> productMapper.toDto(entity) }
            .collect(Collectors.toList())

        val totalSize = jpaQueryFactory
            .selectFrom(qProductEntity)
            .where(eqSeasonType(seasonType), containsName(name))
            .fetch().size
        return PageImpl(content, pageable, totalSize.toLong())
    }

    private fun containsName(name: String?): Predicate? {
        name ?: return null;
        return qProductEntity.name.contains(name!!)

    }

    private fun eqSeasonType(seasonType: Integer?): Predicate? {
        seasonType ?: return null
        return qProductEntity.seasonType.eq(seasonType!!.toInt())
    }

}
