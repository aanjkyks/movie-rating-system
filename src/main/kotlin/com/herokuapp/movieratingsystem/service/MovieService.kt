package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person


interface MovieService {
    fun saveMovie(movie: Movie): Movie
    fun findMovies(name: String? = null, dName: String? = null): List<Movie>
    fun findAllMovies(): List<Movie>
    fun findAllMovieActors(): List<MovieActor>
    fun findByDirector(director: Person): List<Movie>
    fun findById(id: Long): Movie
}