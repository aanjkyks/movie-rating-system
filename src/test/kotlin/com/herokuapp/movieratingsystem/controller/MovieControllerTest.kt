package com.herokuapp.movieratingsystem.controller

import com.herokuapp.movieratingsystem.dto.MovieDto
import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.mapper.MovieMapper
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.PersonService
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Pageable

@ExtendWith(MockitoExtension::class)
internal class MovieControllerTest {
    val movieMapper: MovieMapper = mock()
    val movieService: MovieService = mock()
    val personService: PersonService = mock()

    val movieController = MovieController(movieMapper, movieService, personService)

    @Test
    fun getMovies() {
        whenever(movieMapper.singleMovieToDto(any())).thenReturn(MovieDto())
        val dName = "director name"
        val name = "name"
        movieController.getMovies(name, dName)
        verify(movieService, times(1)).findMovies(name, dName,Pageable.unpaged())
    }

    @Test
    fun getMovie() {
        whenever(movieMapper.singleMovieToDto(any())).thenReturn(MovieDto())
        movieController.getMovie(1)
        verify(movieService).findById(1)
    }

    @Test
    fun createMovie() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        movieController.createMovie(MovieDto())
        verify(movieService).saveMovie(any())
    }

    @Test
    fun createMovieWithId() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        val singleMovieDTO = MovieDto()
        singleMovieDTO.id = 2
        assertThrows<MVRInvalidArgumentException> { movieController.createMovie(singleMovieDTO) }
        verify(movieService, never()).saveMovie(any())
    }

    @Test
    fun updateMovieNoId() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        assertThrows<MVRInvalidArgumentException> { movieController.updateMovie(MovieDto()) }
        verify(movieService, never()).saveMovie(any())
    }

    @Test
    fun updateMovie() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        val singleMovieDTO = MovieDto()
        singleMovieDTO.id = 2
        movieController.updateMovie(singleMovieDTO)
        verify(movieService, times(1)).saveMovie(any())
    }
}