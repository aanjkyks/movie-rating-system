package com.herokuapp.movieratingsystem.security.controller

import com.herokuapp.movieratingsystem.security.JwtTokenUtil
import com.herokuapp.movieratingsystem.security.model.AuthenticationRequest
import com.herokuapp.movieratingsystem.security.model.AuthenticationResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val authenticationManager: AuthenticationManager,
                     private val jwtTokenUtil: JwtTokenUtil,
                     private val userDetailsService: UserDetailsService) {

    @PostMapping(value = ["/api/login"])
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*> {
        try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password)
            )
        } catch (e: BadCredentialsException) {
            throw Exception("Incorrect username or password", e)
        }
        val userDetails: UserDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.username)
        val jwt: String = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(jwt))
    }
}