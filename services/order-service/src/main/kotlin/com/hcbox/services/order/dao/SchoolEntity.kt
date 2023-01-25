package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "SCHOOL")
class SchoolEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotNull
    @Column(name = "NAME", length = 100, nullable = false)
    var name: String,
    @Column(name = "STAFF_NAME", length = 100)
    var staffName: String? = null,
    @Column(name = "PHONE", length = 15)
    var phone: String? = null,
) : BaseEntity()
