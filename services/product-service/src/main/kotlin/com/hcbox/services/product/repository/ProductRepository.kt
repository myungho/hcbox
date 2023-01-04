package com.hcbox.services.product.repository

import com.hcbox.services.product.dao.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long>
