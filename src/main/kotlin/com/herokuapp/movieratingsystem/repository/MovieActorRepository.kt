package com.herokuapp.movieratingsystem.repository


import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface MovieActorRepository : JpaRepository<MovieActor, Long> {
    fun findByActor(actor: Person): List<MovieActor>
    fun deleteByMovie(movie: Movie)
}