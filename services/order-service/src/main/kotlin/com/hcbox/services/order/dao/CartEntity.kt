package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import lombok.ToString
import javax.persistence.*

@Entity
@Table(
    name = "CART",
    indexes = [Index(
        name = "I01_CART",
        columnList = "MEMBER_ID"
    )],
    uniqueConstraints = [UniqueConstraint(
        name = "UK1_CART",
        columnNames = ["MEMBER_ID", "PRODUCT_ID"]
    )]
)
class CartEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CART_ID")
    @SequenceGenerator(
        name = "SQ_CART_ID",
        sequenceName = "SQ_CART_ID",
        allocationSize = 1
    )
    @Column(name = "ID", nullable = false, precision = 20)
    val id: Long = 0,

    @Column(name = "MEMBER_ID")
    var memberId: Long,

    @Column(name = "PRODUCT_ID")
    var productId: Long,

    @Column(name = "QUANTITY", nullable = false, precision = 10)
    var quantity: Long

) : BaseEntity()
