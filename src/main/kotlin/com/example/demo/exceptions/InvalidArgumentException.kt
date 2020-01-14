package com.example.demo.exceptions

class InvalidArgumentException : Exception {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}