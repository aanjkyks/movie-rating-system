package com.herokuapp.movieratingsystem.exceptions

class MVREntityNotFoundException : Exception {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}