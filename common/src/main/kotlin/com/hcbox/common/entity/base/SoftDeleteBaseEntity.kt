package com.hcbox.common.entity.base

import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class SoftDeleteBaseEntity {
    var isDeleted: Boolean = false
}
