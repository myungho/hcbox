package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import lombok.ToString
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
class OrderDetailEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ORDER_ID")
    @SequenceGenerator(
        name = "SQ_CART_ID",
        sequenceName = "SQ_CART_ID",
        allocationSize = 1
    )
    @Column(name = "ID", nullable = false, precision = 20)
    val id: Long = 0,

    @Column(name = "ORDER_ID")
    var orderId: Long,

    @Column(name = "PRODUCT_ID")
    var productId: Long,

    @Column(name = "QUANTITY", nullable = false, precision = 3)
    var quantity: Long

) : BaseEntity()
