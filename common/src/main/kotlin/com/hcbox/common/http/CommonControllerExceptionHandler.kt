package com.hcbox.common.http

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

open class CommonControllerExceptionHandler {

    companion object {
        private val log = LoggerFactory.getLogger(CommonControllerExceptionHandler::class.java)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(
        NotFoundException::class
    )
    @ResponseBody
    fun handleNotFoundExceptions(
        request: ServerHttpRequest?, ex: java.lang.Exception?
    ): HttpErrorInfo? {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request!!, ex!!)
    }

    private fun createHttpErrorInfo(
        httpStatus: HttpStatus, request: ServerHttpRequest, ex: Exception
    ): HttpErrorInfo? {
        val path = request.path.pathWithinApplication().value()
        val message = ex.message
        log.debug(
            "Returning HTTP status: {} for path: {}, message: {}",
            httpStatus,
            path,
            message
        )
        return HttpErrorInfo(httpStatus, path, message)
    }
}
