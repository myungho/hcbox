package com.hcbox.services.order.repository.impl

import com.hcbox.api.dto.OrderDto
import com.hcbox.services.order.dao.OrderEntity
import com.hcbox.services.order.dao.QOrderEntity
import com.hcbox.services.order.mapper.OrderMapper
import com.hcbox.services.order.repository.OrderRepositoryCustom
import com.querydsl.core.types.Predicate
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import java.util.stream.Collectors

class OrderRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory,
    private val orderMapper: OrderMapper
) :
    QuerydslRepositorySupport(
        OrderEntity::class.java
    ), OrderRepositoryCustom {
    private val qOrderEntity = QOrderEntity.orderEntity

    private fun containsStudentName(studentName: String?): Predicate? {
        studentName ?: return null
        return qOrderEntity.studentName.contains(studentName)

    }

    override fun findAllByOptions(
        studentName: String?,
        statusCode: String?,
        schoolId: Long?,
        pageable: PageRequest
    ): Page<OrderDto.OrderReadDto> {
        val content = jpaQueryFactory
            .selectFrom(qOrderEntity)
            .where(containsStudentName(studentName), eqStatusCode(statusCode), eqSchoolId(schoolId))
            .offset(pageable!!.offset)
            .limit(pageable.pageSize.toLong())
            .fetch().stream().map { entity -> orderMapper.toDto(entity) }
            .collect(Collectors.toList())

        val totalSize = jpaQueryFactory
            .selectFrom(qOrderEntity)
            .where(containsStudentName(studentName), eqStatusCode(statusCode), eqSchoolId(schoolId))
            .fetch().size

        return PageImpl(content, pageable, totalSize.toLong())
    }

    private fun eqSchoolId(schoolId: Long?): Predicate? {
        schoolId ?: return null
        return qOrderEntity.schoolEntity.id.eq(schoolId)
    }

    private fun eqStatusCode(statusCode: String?): Predicate? {
        statusCode ?: return null
        return qOrderEntity.statusCode.eq(statusCode)
    }
}
