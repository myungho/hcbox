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
        columnNames = ["CODE"]
    )]
)
class ProductEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NotNull
    @Column(name = "NAME", length = 100)
    var name: String,
    @Column(name = "PRICE", length = 10)
    var price: Long,
    @Column(name = "DISCOUNT", length = 4)
    var discount: Long,
    @Column(name = "STOCK", length = 100)
    var stock: Long,
    @Column(name = "IMAGE_PATH1", length = 100)
    var imagePath1: String?,
    @Column(name = "IMAGE_PATH2", length = 100)
    var imagePath2: String?,
    @Column(name = "IMAGE_PATH3", length = 100)
    var imagePath3: String?,
    @Column(name = "DETAIL", length = 100)
    var detail: String?,
    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    var code: String,
    @Column(name = "BRAND_CODE", nullable = false, length = 5)
    var brandCode: String,
) : BaseEntity()
