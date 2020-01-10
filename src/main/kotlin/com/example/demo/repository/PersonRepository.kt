package com.example.demo.repository

import com.example.demo.model.Movie
import com.example.demo.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {
    fun findByName(name: String): List<Person>
    fun findByMovie(movie: Movie): List<Person>
}