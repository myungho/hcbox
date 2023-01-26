package com.hcbox.services.order.controller

import com.hcbox.common.http.CommonControllerExceptionHandler
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler : CommonControllerExceptionHandler() {

    companion object {
        private val log = LoggerFactory.getLogger(ControllerExceptionHandler::class.java)
    }

}
