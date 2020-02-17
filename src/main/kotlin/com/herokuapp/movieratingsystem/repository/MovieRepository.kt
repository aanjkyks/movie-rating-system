package com.herokuapp.movieratingsystem.repository

import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Movie>
    fun findByDirector(director: Person): List<Movie>
    fun findByDirectorIn(directors: List<Person>): List<Movie>
}