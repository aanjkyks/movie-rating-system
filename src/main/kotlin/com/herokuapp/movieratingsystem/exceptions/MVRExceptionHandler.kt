package com.herokuapp.movieratingsystem.exceptions

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class MVRExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [(MVREntityNotFoundException::class)])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEntityNotFound(e: MVREntityNotFoundException): ErrorDto {
        logger.warn(e.localizedMessage, e)
        return ErrorDto(e.localizedMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(MVRInvalidArgumentException::class)])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidArgument(e: MVRInvalidArgumentException): ErrorDto {
        logger.warn(e.localizedMessage, e)
        return ErrorDto(e.localizedMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(MVRInternalErrorException::class)])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInternalError(e: MVRInternalErrorException): ErrorDto {
        logger.warn(e.localizedMessage, e)
        return ErrorDto(e.localizedMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    class ErrorDto(val message: String, val status: HttpStatus) {
        val timestamp: Instant = Instant.now()
    }
}