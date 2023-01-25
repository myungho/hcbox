package com.hcbox.services.product.repository

import com.hcbox.services.product.dao.SchoolEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolRepository : JpaRepository<SchoolEntity, Long>, SchoolRepositoryCustom
