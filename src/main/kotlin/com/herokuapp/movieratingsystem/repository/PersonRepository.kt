package com.herokuapp.movieratingsystem.repository

import com.herokuapp.movieratingsystem.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Person>
}