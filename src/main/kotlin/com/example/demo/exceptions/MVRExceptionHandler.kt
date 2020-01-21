package com.example.demo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class MVRExceptionHandler {

    @ExceptionHandler(value = [(MVREntityNotFoundException::class)])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEntityNotFound(e: MVREntityNotFoundException): ErrorDto {
        return ErrorDto(e.localizedMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(MVRInvalidArgumentException::class)])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidArgument(e: MVRInvalidArgumentException): ErrorDto {
        return ErrorDto(e.localizedMessage, HttpStatus.BAD_REQUEST)
    }

    class ErrorDto(val message: String, val status: HttpStatus) {
        val timestamp: Instant = Instant.now()
    }
}