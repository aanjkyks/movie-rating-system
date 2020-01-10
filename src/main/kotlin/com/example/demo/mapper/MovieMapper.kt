package com.example.demo.mapper

import com.example.demo.dto.MovieDTO
import com.example.demo.exceptions.EntityNotFoundException
import com.example.demo.model.Movie
import com.example.demo.model.MovieActor
import com.example.demo.model.Person
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class MovieMapper {
    private var modelMapper = ModelMapper()

    fun movieToDto(movie: Movie): MovieDTO {
        val movieDTO = MovieDTO()
        modelMapper.map(movie, movieDTO)
        val size = movie.ratings.size.toDouble()
        movieDTO.avgRating = movie.ratings
                .map { it.value }
                .sum()
                .div(if (size == 0.0) 1.0 else size)
        movieDTO.totalRatings = size.toInt()
        return movieDTO
    }

    fun movieListTodtoList(movies: List<Movie>): List<MovieDTO> {
        return movies.map { movieToDto(it) }
    }

    fun dtoToMovie(movieDTO: MovieDTO, personList: List<Person>): Movie {
        val movie = Movie()
        modelMapper.map(movieDTO, movie)
        for (actor in movieDTO.actors) {
            val movieActor = MovieActor()
            movieActor.pk.actor = personList.find { person -> person.id == actor.id }
                    ?: throw EntityNotFoundException("No such person with id " + actor.id, null)
            movieActor.pk.movie = movie
            movieActor.role = actor.role
        }
        return movie
    }
}