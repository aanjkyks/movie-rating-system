package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import org.springframework.data.domain.Page


interface MovieService {
    fun saveMovie(movie: Movie): Movie
    fun findMovies(name: String?, dName: String?): List<Movie>
    fun findAllMovies(): List<Movie>
    fun findAllMovieActors(): List<MovieActor>
    fun findByDirector(director: Person): List<Movie>
    fun findById(id: Long): Movie
    fun findAllPaginated(page: Int, size: Int): Page<Movie>
}