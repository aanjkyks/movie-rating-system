package com.example.demo.exceptions

class EntityNotFoundException(override val message: String?, override val cause: Throwable?) : Exception(message, cause)