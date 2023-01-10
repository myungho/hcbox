package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import lombok.ToString
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "ORDER",
    indexes = [Index(
        name = "I01_CART",
        columnList = "MEMBER_ID"
    )],
    uniqueConstraints = [UniqueConstraint(
        name = "UK1_CART",
        columnNames = ["MEMBER_ID", "PRODUCT_ID"]
    )]
)
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ORDER_ID")
    @SequenceGenerator(
        name = "SQ_CART_ID",
        sequenceName = "SQ_CART_ID",
        allocationSize = 1
    )
    @Column(name = "ID", nullable = false, precision = 20)
    val id: Long = 0,

    @Column(name = "MEMBER_ID")
    var memberId: Long,

    @Column(name = "COUPON_ID")
    var couponId: Long?,

    @Column(name = "STCD")
    var stcd: String,

    @Column(name = "ORDER_DATE")
    var orderDate: Date?,

    @Column(name = "PHONE")
    var phone: String?,

    @Column(name = "ADDRESS")
    var address: String,
) : BaseEntity()
