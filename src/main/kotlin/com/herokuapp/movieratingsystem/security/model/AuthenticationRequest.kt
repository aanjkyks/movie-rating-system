package com.herokuapp.movieratingsystem.security.model

import java.io.Serializable


class AuthenticationRequest() : Serializable {
    lateinit var username: String
    lateinit var password: String

    constructor(username: String, password: String) : this() {
        this.username = username
        this.password = password
    }
}