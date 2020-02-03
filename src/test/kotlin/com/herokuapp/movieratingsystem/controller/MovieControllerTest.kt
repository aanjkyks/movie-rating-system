package com.herokuapp.movieratingsystem.controller

import com.herokuapp.movieratingsystem.dto.SingleMovieDTO
import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.mapper.MovieMapper
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.PersonService
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class MovieControllerTest {
    val movieMapper: MovieMapper = mock()
    val movieService: MovieService = mock()
    val personService: PersonService = mock()

    val movieController = MovieController(movieMapper, movieService, personService)

    @Test
    fun getMovies() {
        whenever(movieMapper.singleMovieToDto(any())).thenReturn(SingleMovieDTO())
        val dName = "director name"
        val name = "name"
        movieController.getMovies(name, dName)
        verify(movieService, times(1)).findMovies(name, dName)
    }

    @Test
    fun getMovie() {
        whenever(movieMapper.singleMovieToDto(any())).thenReturn(SingleMovieDTO())
        movieController.getMovie(1)
        verify(movieService).findById(1)
    }

    @Test
    fun createMovie() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        movieController.createMovie(SingleMovieDTO())
        verify(movieService).saveMovie(any())
    }

    @Test
    internal fun createMovieWithId() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        val singleMovieDTO = SingleMovieDTO()
        singleMovieDTO.id = 2
        assertThrows<MVRInvalidArgumentException> { movieController.createMovie(singleMovieDTO) }
        verify(movieService, never()).saveMovie(any())
    }

    @Test
    fun updateMovieNoId() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        assertThrows<MVRInvalidArgumentException> { movieController.updateMovie(SingleMovieDTO()) }
        verify(movieService, never()).saveMovie(any())
    }

    @Test
    internal fun updateMovie() {
        val movie = MovieTestingUtils.createMovie()
        whenever(movieMapper.dtoToMovie(any(), any())).thenReturn(movie)
        val singleMovieDTO = SingleMovieDTO()
        singleMovieDTO.id = 2
        movieController.updateMovie(singleMovieDTO)
        verify(movieService, times(1)).saveMovie(any())
    }
}