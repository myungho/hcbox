package com.hcbox.services.order.repository

import com.hcbox.services.order.dao.SchoolEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolRepository : JpaRepository<SchoolEntity, Long>, SchoolRepositoryCustom
