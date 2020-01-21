package com.example.demo.service

import com.example.demo.model.Rating
import com.example.demo.repository.RatingRepository
import org.springframework.stereotype.Service

interface IRatingService {
    fun saveRating(rating: Rating): Rating
}

@Service
class RatingServiceImpl(private val ratingRepository: RatingRepository) : IRatingService {
    override fun saveRating(rating: Rating): Rating {
        return ratingRepository.save(rating)
    }
}