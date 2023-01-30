package com.hcbox.services.order.repository

import com.hcbox.services.order.dao.OrderDetailEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderDetailRepository : JpaRepository<OrderDetailEntity, Long>
