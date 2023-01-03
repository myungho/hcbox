package com.hcbox.services.member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@EntityScan("com.hcbox.common.entity")
@SpringBootApplication
class MemberApplication

fun main(args: Array<String>) {
    runApplication<MemberApplication>(*args)
}
