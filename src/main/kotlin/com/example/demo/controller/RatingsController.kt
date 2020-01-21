package com.example.demo.controller

import com.example.demo.dto.RatingDTO
import com.example.demo.mapper.RatingMapper
import com.example.demo.service.MovieService
import com.example.demo.service.RatingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/api/ratings")
class RatingsController(val ratingService: RatingService,
                        val ratingMapper: RatingMapper,
                        val movieService: MovieService) {
    @PostMapping
    @Throws(EntityNotFoundException::class)
    fun createRating(@RequestBody ratingDTO: RatingDTO): RatingDTO {
        return ratingMapper.ratingToDto(ratingService.saveRating(ratingMapper.dtoToRating(ratingDTO, movieService.findAllMovies())))
    }
}