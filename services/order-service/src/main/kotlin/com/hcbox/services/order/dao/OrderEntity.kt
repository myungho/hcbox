package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "`ORDER`",
    indexes = [Index(
        name = "I01_ORDER",
        columnList = "MEMBER_ID"
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

    @Column(name = "MEMBER_ID")
    var memberId: Long? = null

    @Column(name = "COUPON_ID")
    var couponId: Long? = null

    @Column(name = "STATUS_CODE")
    var statusCode: String? = null

    @Column(name = "ORDER_DATE")
    var orderDate: Date? = null

    @Column(name = "PHONE")
    var phone: String? = null

    @Column(name = "ADDRESS")
    var address: String? = null
}

