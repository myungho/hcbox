package com.hcbox.common.entity.base

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity(
    @CreatedDate @Column(
        name = "created_date",
        nullable = false,
        updatable = false,
        columnDefinition = "DATE"
    ) var createdDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate @Column(
        name = "updated_date",
        columnDefinition = "DATE"
    ) var updatedDate: LocalDateTime = LocalDateTime.now(),
    @CreatedBy @Column(name = "created_by", updatable = false) var createBy: String = "",
    @LastModifiedBy @Column(name = "updated_by") var updatedBy: String = "",
) : SoftDeleteBaseEntity()
