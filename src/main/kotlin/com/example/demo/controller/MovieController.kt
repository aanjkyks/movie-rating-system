package com.example.demo.controller

import com.example.demo.dto.MovieDTO
import com.example.demo.mapper.MovieMapper
import com.example.demo.service.IMovieService
import com.example.demo.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MovieController(val movieMapper: MovieMapper,
                      val movieService: IMovieService,
                      val personService: PersonService) {
    @GetMapping("/movies")
    fun getMovies(@RequestParam(required = false) name: String?,
                  @RequestParam(required = false) dName: String?): List<MovieDTO> {
        return movieMapper.movieListTodtoList(movieService.findMovies(name, dName))
    }

    @PostMapping("/movies")
    fun createMovie(@RequestBody movieDTO: MovieDTO): MovieDTO {
        return movieMapper.movieToDto(movieService.saveMovie(movieMapper.dtoToMovie(movieDTO, personService.findAllPeople())))
    }

    @PutMapping("/movies")
    fun updateMovie(@RequestBody movieDTO: MovieDTO) {
        movieService.saveMovie(movieMapper.dtoToMovie(movieDTO, personService.findAllPeople()))
    }
}