package com.hcbox.services.order.repository.impl

import com.hcbox.api.dto.SchoolDto
import com.hcbox.services.order.dao.QSchoolEntity
import com.hcbox.services.order.dao.SchoolEntity
import com.hcbox.services.order.mapper.SchoolMapper
import com.hcbox.services.order.repository.SchoolRepositoryCustom
import com.querydsl.core.types.Predicate
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import java.util.stream.Collectors

class SchoolRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val schoolMapper: SchoolMapper
) :
    QuerydslRepositorySupport(
        SchoolEntity::class.java
    ), SchoolRepositoryCustom {
    private val qSchoolEntity = QSchoolEntity.schoolEntity

    override fun findAllByOptions(
        name: String?,
        pageable: PageRequest
    ): Page<SchoolDto.SchoolReadDto> {
        val content = jpaQueryFactory
            .selectFrom(qSchoolEntity)
            .where(containsName(name))
            .offset(pageable!!.offset)
            .limit(pageable.pageSize.toLong())
            .fetch().stream().map { entity -> schoolMapper.toDto(entity) }
            .collect(Collectors.toList())

        val totalSize = jpaQueryFactory
            .selectFrom(qSchoolEntity)
            .where(containsName(name))
            .fetch().size
        return PageImpl(content, pageable, totalSize.toLong())
    }

    private fun containsName(name: String?): Predicate? {
        name ?: return null;
        return qSchoolEntity.name.contains(name!!)

    }
}
