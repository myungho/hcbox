package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "SCHOOL")
class SchoolEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SCHOOL_ID")
    @SequenceGenerator(
        name = "SQ_SCHOOL_ID",
        sequenceName = "SQ_SCHOOL_ID",
        initialValue = 10
    )
    var id: Long? = null,

    @NotNull
    @Column(name = "NAME", length = 100, nullable = false)
    var name: String? = null,

    @Column(name = "STAFF_NAME", length = 100)
    var staffName: String? = null,

    @Column(name = "PHONE", length = 15)
    var phone: String? = null,
) : BaseEntity()
