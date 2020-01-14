package com.example.demo.exceptions

class EntityNotFoundException : Exception {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}