package com.example.demo.controller

import com.example.demo.dto.MovieDTO
import com.example.demo.exceptions.MVRInvalidArgumentException
import com.example.demo.mapper.MovieMapper
import com.example.demo.service.MovieService
import com.example.demo.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/movies")
class MovieController(val movieMapper: MovieMapper,
                      val movieService: MovieService,
                      val personService: PersonService) {
    @GetMapping
    fun getMovies(@RequestParam(required = false) name: String?,
                  @RequestParam(required = false) dName: String?): List<MovieDTO> {
        return movieMapper.movieListToDtoList(movieService.findMovies(name, dName))
    }

    @PostMapping
    fun createMovie(@RequestBody movieDTO: MovieDTO): MovieDTO {
        movieDTO.id?.let {
            throw MVRInvalidArgumentException("Id present for movie to create")
        }
        return movieMapper.movieToDto(movieService.saveMovie(movieMapper.dtoToMovie(movieDTO, personService.findAllPeople())))
    }

    @PutMapping
    fun updateMovie(@RequestBody movieDTO: MovieDTO): MovieDTO {
        movieDTO.id?.let {
            return movieMapper.movieToDto(movieService.saveMovie(movieMapper.dtoToMovie(movieDTO, personService.findAllPeople())))
        }
        throw MVRInvalidArgumentException("No id for movie to update")
    }
}