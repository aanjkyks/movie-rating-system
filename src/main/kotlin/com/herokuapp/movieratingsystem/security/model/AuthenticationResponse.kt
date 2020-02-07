package com.herokuapp.movieratingsystem.security.model

import java.io.Serializable

class AuthenticationResponse(val jwt: String) : Serializable