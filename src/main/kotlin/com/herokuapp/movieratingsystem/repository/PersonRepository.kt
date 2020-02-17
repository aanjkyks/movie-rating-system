package com.herokuapp.movieratingsystem.repository

import com.herokuapp.movieratingsystem.model.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Person>
}