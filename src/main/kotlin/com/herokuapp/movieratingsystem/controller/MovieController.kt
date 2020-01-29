package com.herokuapp.movieratingsystem.controller


import com.herokuapp.movieratingsystem.dto.SingleMovieDTO
import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.mapper.MovieMapper
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/movies")
class MovieController(val movieMapper: MovieMapper,
                      val movieService: MovieService,
                      val personService: PersonService) {
    @GetMapping
    fun getMovies(@RequestParam(required = false) name: String?,
                  @RequestParam(required = false) dName: String?): List<SingleMovieDTO> {
        return movieMapper.movieListToDtoList(movieService.findMovies(name, dName))
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Long): SingleMovieDTO {
        return movieMapper.singleMovieToDto(movieService.findById(id))
    }

    @PostMapping
    fun createMovie(@RequestBody singleMovieDTO: SingleMovieDTO): SingleMovieDTO {
        singleMovieDTO.id?.let {
            throw MVRInvalidArgumentException("Id present for movie to create")
        }
        return movieMapper.singleMovieToDto(movieService.saveMovie(movieMapper.dtoToMovie(singleMovieDTO, personService.findAllPeople())))
    }

    @PutMapping
    fun updateMovie(@RequestBody singleMovieDTO: SingleMovieDTO): SingleMovieDTO {
        singleMovieDTO.id?.let {
            return movieMapper.singleMovieToDto(movieService.saveMovie(movieMapper.dtoToMovie(singleMovieDTO, personService.findAllPeople())))
        }
        throw MVRInvalidArgumentException("No id for movie to update")
    }
}