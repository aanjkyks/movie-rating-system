package com.example.demo.service

import com.example.demo.model.Movie
import com.example.demo.model.MovieActor

interface MovieService {
    fun saveMovie(movie: Movie): Movie
    fun findMovies(name: String?, dName: String?): List<Movie>
    fun findAllMovies(): List<Movie>
    fun findAllMovieActors(): List<MovieActor>
}