package com.herokuapp.movieratingsystem.repository

import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Long> {
    fun findByNameContainingIgnoreCase(name: String): List<Movie>
    fun findByDirector(director: Person): List<Movie>
}