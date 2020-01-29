package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.model.Rating


interface RatingService {
    fun saveRating(rating: Rating): Rating
}