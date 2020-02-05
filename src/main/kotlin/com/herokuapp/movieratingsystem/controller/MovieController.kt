package com.herokuapp.movieratingsystem.controller


import com.herokuapp.movieratingsystem.dto.MovieDto
import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.mapper.MovieMapper
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/movies")
class MovieController(private val movieMapper: MovieMapper,
                      private val movieService: MovieService,
                      private val personService: PersonService) {
    @GetMapping
    fun getMovies(@RequestParam(required = false) name: String?,
                  @RequestParam(required = false) dName: String?): List<MovieDto> {
        return movieMapper.movieListToDtoList(movieService.findMovies(name, dName))
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Long): MovieDto {
        return movieMapper.singleMovieToDto(movieService.findById(id))
    }

    @PostMapping
    fun createMovie(@RequestBody movieDto: MovieDto): MovieDto {
        movieDto.id?.let {
            throw MVRInvalidArgumentException("Id present for movie to create")
        }
        return movieMapper.singleMovieToDto(movieService.saveMovie(movieMapper.dtoToMovie(movieDto, personService.findAllPeople())))
    }

    @PutMapping
    fun updateMovie(@RequestBody movieDto: MovieDto): MovieDto {
        movieDto.id?.let {
            return movieMapper.singleMovieToDto(movieService.saveMovie(movieMapper.dtoToMovie(movieDto, personService.findAllPeople())))
        }
        throw MVRInvalidArgumentException("No id for movie to update")
    }
}