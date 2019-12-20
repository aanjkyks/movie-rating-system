package com.example.demo.controller

import com.example.demo.dto.MovieDTO
import com.example.demo.mapper.MovieMapper
import com.example.demo.model.Movie
import com.example.demo.repository.MovieRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(var movieRepository: MovieRepository,
                      var movieMapper: MovieMapper) {
    @GetMapping("/movies")
    fun getMovies(): List<MovieDTO> {
        return movieMapper.movieListTodtoList(movieRepository.findAll())
    }

    @PostMapping("/movies")
    fun createMovie(@RequestBody movie: Movie): Movie {
        return movieRepository.save(movie)
    }
}