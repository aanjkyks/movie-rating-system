package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.model.Rating
import com.herokuapp.movieratingsystem.repository.RatingRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class RatingServiceImplTest {

    val ratingRepository: RatingRepository = mock()

    val ratingService: RatingService = RatingServiceImpl(ratingRepository)

    @Test
    fun saveRating() {
        whenever(ratingRepository.save(any<Rating>())).thenReturn(Rating())
        ratingService.saveRating(Rating())
        verify(ratingRepository, times(1)).save(any<Rating>())
    }

    @Test
    internal fun saveRatingValueMoreThanTen() {
        assertThrows<MVRInvalidArgumentException> { ratingService.saveRating(Rating(value = 15)) }
        verify(ratingRepository, never()).save(any<Rating>())
    }
}