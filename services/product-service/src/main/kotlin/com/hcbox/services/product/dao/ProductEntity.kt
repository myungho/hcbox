package com.hcbox.services.product.dao

import com.hcbox.common.entity.base.BaseEntity
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(
    name = "PRODUCT",
    indexes = [Index(
        name = "I01_PRODUCT",
        columnList = "NAME"
    )],
    uniqueConstraints = [UniqueConstraint(
        name = "UK1_PRODUCT",
        columnNames = ["TYPE_CODE"]
    )]
)
class ProductEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotNull
    @Column(name = "SEASON_TYPE", length = 1, nullable = false)
    var seasonType: Integer,
    @NotNull
    @Column(name = "NAME", length = 100, nullable = false)
    var name: String,
    @NotNull
    @Column(name = "TYPE_CODE", length = 10, nullable = false)
    var typeCode: String,
    @Column(name = "PRICE", length = 10)
    var price: Long? = null,
) : BaseEntity()
