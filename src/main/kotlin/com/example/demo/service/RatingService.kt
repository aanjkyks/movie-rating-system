package com.example.demo.service

import com.example.demo.model.Rating

interface RatingService {
    fun saveRating(rating: Rating): Rating
}