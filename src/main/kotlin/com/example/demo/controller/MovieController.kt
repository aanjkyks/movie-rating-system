package com.example.demo.controller

import com.example.demo.dto.MovieDTO
import com.example.demo.mapper.MovieMapper
import com.example.demo.service.IMovieService
import org.springframework.web.bind.annotation.*

@RestController
class MovieController(val movieMapper: MovieMapper,
                      val movieService: IMovieService) {
    @GetMapping("/movies")
    fun getMovies(@RequestParam(required = false) name: String?,
                  @RequestParam(required = false) dName: String?): List<MovieDTO> {
        return movieMapper.movieListTodtoList(movieService.findMovies(name, dName))
    }

    @PostMapping("/movies")
    fun createMovie(@RequestBody movieDTO: MovieDTO): MovieDTO {
        return movieMapper.movieToDto(movieService.saveMovie(movieMapper.dtoToMovie(movieDTO)))
    }

    @PutMapping("/movies")
    fun updateMovie(@RequestBody movieDTO: MovieDTO) {
        movieService.saveMovie(movieMapper.dtoToMovie(movieDTO))
    }
}