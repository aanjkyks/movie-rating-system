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
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
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
        whenever(movieRepository.findAll(any<Pageable>())).thenReturn(PageImpl(listOf(Movie())))
        movieServiceImpl.findMovies(null, null, Pageable.unpaged())
        verify(movieRepository, times(1)).findAll(any<Pageable>())
    }

    @Test
    internal fun findMoviesNullName() {
        whenever(personRepository.findByNameContainingIgnoreCase(any(), any())).thenReturn(PageImpl(listOf(Person())))
        movieServiceImpl.findMovies(dName = "Director", pageable = Pageable.unpaged(), name = null)
        verify(personRepository, times(1)).findByNameContainingIgnoreCase(any(), any())
        verify(movieRepository, times(1)).findByDirectorIn(any())
        verify(movieRepository, never()).findByNameContainingIgnoreCase(any(), any())
    }

    @Test
    internal fun findMoviesNullDirectorName() {
        movieServiceImpl.findMovies(name = "Movie name")
        verify(movieRepository, times(1)).findByNameContainingIgnoreCase(any(), any())
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