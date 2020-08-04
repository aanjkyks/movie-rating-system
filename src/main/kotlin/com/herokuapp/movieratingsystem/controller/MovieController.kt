package com.herokuapp.movieratingsystem.controller


import com.herokuapp.movieratingsystem.dto.MovieDto
import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.mapper.MovieMapper
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/movies")
class MovieController(private val movieMapper: MovieMapper,
                      private val movieService: MovieService,
                      private val personService: PersonService) {
    @GetMapping
    fun getMovies(@RequestParam(required = false) name: String?,
                  @RequestParam(required = false) dName: String?): List<MovieDto> {

        val blankCheckedName = if (name.isNullOrBlank()) null else name
        val blackCheckedDirectorName = if (dName.isNullOrBlank()) null else dName

        return movieMapper.movieListToDtoList(movieService.findMovies(blankCheckedName, blackCheckedDirectorName))
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