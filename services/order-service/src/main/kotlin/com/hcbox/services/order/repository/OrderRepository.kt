package com.hcbox.services.order.repository

import com.hcbox.services.order.dao.OrderEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long>, OrderRepositoryCustom {
    @EntityGraph(
        attributePaths = ["schoolEntity", "orderDetailEntityList"],
        type = EntityGraph.EntityGraphType.LOAD
    )
    override fun findById(id: Long): Optional<OrderEntity>
}
