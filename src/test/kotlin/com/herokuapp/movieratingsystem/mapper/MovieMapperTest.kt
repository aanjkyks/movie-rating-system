package com.herokuapp.movieratingsystem.mapper

import com.herokuapp.movieratingsystem.dto.MovieDto
import com.herokuapp.movieratingsystem.dto.PersonDTO
import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.model.Rating
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class MovieMapperTest {

    val movieMapper: MovieMapper = spy()
    val mainRole = "Main Role"

    @Test
    fun singleMovieToDto() {
        val movie = MovieTestingUtils.createMovie()
        movie.ratings = listOf(Rating(movie = movie, value = 10), Rating(movie = movie, value = 5))

        val expected = MovieDto(
                name = movie.name,
                avgRating = 7.5,
                totalRatings = 2,
                description = movie.description,
                id = movie.id,
                director = PersonDTO(
                        id = movie.director.id,
                        name = movie.director.name,
                        role = "Director"))

        val personDto = PersonDTO(name = "Actor", role = mainRole, id = 2)
        expected.actors = listOf(personDto)

        assertEquals(expected, movieMapper.singleMovieToDto(movie))
    }

    @Test
    fun movieListToDtoList() {
        val movies = listOf(Movie())
        movieMapper.movieListToDtoList(movies)
        verify(movieMapper, times(movies.size)).movieToBatchDto(any())
    }

    @Test
    fun movieToBatchDto() {
        val movieToBatchDto = movieMapper.movieToBatchDto(MovieTestingUtils.createMovie())
        assertTrue(movieToBatchDto.actors.isEmpty())
        assertTrue(movieToBatchDto.director.photo.isNullOrEmpty())
    }

    @Test
    fun dtoToMovie() {
        val secondaryRole = "Secondary"
        val movieDto = MovieDto(
                director = PersonDTO(id = 1),
                id = 1,
                name = "Movie",
                description = "desc",
                actors = listOf(
                        PersonDTO(id = 2, role = mainRole),
                        PersonDTO(id = 3, role = secondaryRole)))
        val expected = Movie(
                id = 1,
                actors = listOf(
                        MovieActor(actor = Person(id = 2), role = mainRole),
                        MovieActor(actor = Person(id = 3), role = secondaryRole)),
                description = "desc",
                name = "Movie",
                director = Person(id = 1))
        expected.actors.forEach { it.movie = expected }
        assertEquals(expected, movieMapper.dtoToMovie(movieDto, listOf(Person(id = 1), Person(id = 2), Person(id = 3))))
    }
}