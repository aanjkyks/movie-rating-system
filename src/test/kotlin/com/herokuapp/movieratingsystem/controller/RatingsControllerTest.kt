package com.herokuapp.movieratingsystem.controller

import com.herokuapp.movieratingsystem.dto.RatingDTO
import com.herokuapp.movieratingsystem.mapper.RatingMapper
import com.herokuapp.movieratingsystem.model.Rating
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.RatingService
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class RatingsControllerTest {
    val ratingService: RatingService = mock()
    val ratingMapper: RatingMapper = mock()
    val movieService: MovieService = mock()

    val ratingController = RatingsController(ratingService, ratingMapper, movieService)

    @Test
    fun createRating() {
        whenever(ratingMapper.dtoToRating(any(), any())).thenReturn(Rating())
        whenever(movieService.findById(any())).thenReturn(MovieTestingUtils.createMovie())
        whenever(ratingMapper.ratingToDto(any())).thenReturn(RatingDTO())
        whenever(ratingService.saveRating(any())).thenReturn(Rating())
        ratingController.createRating(RatingDTO())
        verify(ratingService, times(1)).saveRating(any())
    }
}