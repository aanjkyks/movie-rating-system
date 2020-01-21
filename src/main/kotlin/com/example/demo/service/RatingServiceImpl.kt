package com.example.demo.service

import com.example.demo.model.Rating
import com.example.demo.repository.RatingRepository
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl(private val ratingRepository: RatingRepository) : RatingService {
    override fun saveRating(rating: Rating): Rating {
        return ratingRepository.save(rating)
    }
}