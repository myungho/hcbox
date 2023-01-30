package com.hcbox.services.order.dao

import com.hcbox.common.entity.base.BaseEntity
import javax.persistence.*

@Entity
@Table(
    name = "ORDER_DETAIL",
    indexes = [Index(
        name = "I01_ORDER_DETAIL",
        columnList = "ORDER_ID"
    )],
    uniqueConstraints = [UniqueConstraint(
        name = "UK1_ORDER_DETAIL",
        columnNames = ["ORDER_ID", "PRODUCT_ID"]
    )]
)
class OrderDetailEntity : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ORDER_DETAIL_ID")
    @SequenceGenerator(
        name = "SQ_ORDER_DETAIL_ID",
        sequenceName = "SQ_ORDER_DETAIL_ID",
        allocationSize = 1
    )
    @Column(name = "ID", nullable = false, precision = 20)
    val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    var orderEntity: OrderEntity? = null

    @Column(name = "PRODUCT_ID")
    var productId: Long? = null

    @Column(name = "QUANTITY", nullable = false, precision = 3)
    var quantity: Long? = null
}
