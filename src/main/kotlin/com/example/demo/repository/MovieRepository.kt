package com.example.demo.repository

import com.example.demo.model.Movie
import com.example.demo.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Long> {
    fun findByNameContaining(name: String?): List<Movie>
    fun findByDirector(director: Person?): List<Movie>
}