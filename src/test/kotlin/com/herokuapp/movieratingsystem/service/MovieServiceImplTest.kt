package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.MovieActorRepository
import com.herokuapp.movieratingsystem.repository.MovieRepository
import com.herokuapp.movieratingsystem.repository.PersonRepository
import com.herokuapp.movieratingsystem.repository.RatingRepository
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class MovieServiceImplTest {

    val movieRepository: MovieRepository = mock()
    val ratingRepository: RatingRepository = mock()
    val personRepository: PersonRepository = mock()
    val movieActorRepository: MovieActorRepository = mock()

    val movieServiceImpl = MovieServiceImpl(movieRepository, ratingRepository, personRepository, movieActorRepository)

    @BeforeEach
    internal fun setUp() {
        whenever(movieRepository.save(any<Movie>())).thenReturn(Movie())
    }

    @Test
    fun saveMovie() {
        movieServiceImpl.saveMovie(MovieTestingUtils.createMovie())
        verify(movieRepository, times(1)).save(any<Movie>())
        verify(ratingRepository, times(1)).findByMovie(any())
    }

    @Test
    internal fun saveMovieNoId() {
        val movie = MovieTestingUtils.createMovie()
        movie.id = null
        movieServiceImpl.saveMovie(movie)
        verify(movieRepository, times(1)).save(any<Movie>())
        verify(ratingRepository, never()).findByMovie(any())

    }

    @Test
    fun findMoviesNullParam() {
        movieServiceImpl.findMovies()
        verify(movieRepository, times(1)).findAll()
    }

    @Test
    internal fun findMoviesNullName() {
        whenever(personRepository.findByName(any())).thenReturn(listOf(Person()))
        movieServiceImpl.findMovies(dName = "Director")
        verify(personRepository, times(1)).findByName("Director")
        verify(movieRepository, times(1)).findByDirector(any())
        verify(movieRepository, never()).findByNameContaining(any())
    }

    @Test
    internal fun findMoviesNullDirectorName() {
        movieServiceImpl.findMovies(name = "Movie name")
        verify(movieRepository, times(1)).findByNameContaining(any())
        verify(movieRepository, never()).findAll()
    }

    @Test
    fun findAllMovies() {
        movieServiceImpl.findAllMovies()
        verify(movieRepository).findAll()
    }

    @Test
    fun findAllMovieActors() {
        movieServiceImpl.findAllMovieActors()
        verify(movieActorRepository).findAll()
    }

    @Test
    fun findByDirector() {
        movieServiceImpl.findByDirector(Person())
        verify(movieRepository).findByDirector(any())
    }

    @Test
    fun findByIdSuccess() {
        whenever(movieRepository.findById(any())).thenReturn(Optional.of(Movie()))
        movieServiceImpl.findById(1)
        verify(movieRepository).findById(1)
    }

    @Test
    internal fun findByIdThrows() {
        whenever(movieRepository.findById(1)).thenReturn(Optional.empty())
        assertThrows<MVREntityNotFoundException> { movieServiceImpl.findById(1) }
    }
}