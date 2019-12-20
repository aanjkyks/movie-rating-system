package com.example.demo.repository

import com.example.demo.model.Movie
import com.example.demo.model.Rating
import org.springframework.data.jpa.repository.JpaRepository

interface RatingRepository : JpaRepository<Rating, Long> {
    fun findByMovie(movie: Movie): List<Rating>
}