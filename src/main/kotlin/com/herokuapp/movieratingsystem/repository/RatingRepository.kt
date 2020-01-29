package com.herokuapp.movieratingsystem.repository

import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.Rating
import org.springframework.data.jpa.repository.JpaRepository

interface RatingRepository : JpaRepository<Rating, Long> {
    fun findByMovie(movie: Movie): List<Rating>
}