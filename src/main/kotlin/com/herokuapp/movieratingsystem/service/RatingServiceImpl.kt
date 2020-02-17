package com.herokuapp.movieratingsystem.service


import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.model.Rating
import com.herokuapp.movieratingsystem.repository.RatingRepository
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl(private val ratingRepository: RatingRepository,
                        private val movieService: MovieService) : RatingService {
    override fun saveRating(rating: Rating): Rating {
        if (rating.value > 10 || rating.value < 0) {
            throw MVRInvalidArgumentException("Rating can only be in range 0 - 10")
        }
        val ratings = mutableListOf(rating)
        ratings.addAll(rating.movie.ratings)
        rating.movie.avgRating = ratings.map { it.value }.sum().div(ratings.size.toDouble())
        movieService.saveMovie(rating.movie)
        return ratingRepository.save(rating)
    }
}