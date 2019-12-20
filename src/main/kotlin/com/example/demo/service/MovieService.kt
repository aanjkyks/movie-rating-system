package com.example.demo.service

import com.example.demo.model.Movie
import com.example.demo.repository.MovieRepository
import com.example.demo.repository.RatingRepository
import org.springframework.stereotype.Component

@Component
class MovieService(
        val movieRepository: MovieRepository,
        val ratingRepository: RatingRepository
) {
    fun saveMovie(movie: Movie): Movie {
        movie.id?.let { movie.ratings = ratingRepository.findByMovie(movie) }
        return movieRepository.save(movie)
    }
}