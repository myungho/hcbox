package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(
    name = "`ORDER`",
    indexes = [Index(
        name = "I01_ORDER",
        columnList = "STUDENT_NAME, PHONE"
    )]
)
class OrderEntity : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ORDER_ID")
    @SequenceGenerator(
        name = "SQ_ORDER_ID",
        sequenceName = "SQ_ORDER_ID",
        allocationSize = 1
    )
    @Column(name = "ID", nullable = false, precision = 20)
    val id: Long = 0

    @Column(name = "STUDENT_NAME", nullable = false, length = 100)
    var studentName: String? = null

    @Column(name = "STATUS_CODE")
    var statusCode: String? = null

    @Column(name = "ORDER_DATE")
    var orderDate: Date? = null

    @Column(name = "PHONE", nullable = false, length = 15)
    var phone: String? = null

    @Column(name = "ADDRESS")
    var address: String? = null

    @ManyToOne
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    var schoolEntity: @NotNull SchoolEntity? = null

    @OneToMany(mappedBy = "orderEntity")
    var orderDetailEntityList: MutableList<OrderDetailEntity>? = null
}

