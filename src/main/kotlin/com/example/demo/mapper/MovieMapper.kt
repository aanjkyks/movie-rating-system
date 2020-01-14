package com.example.demo.mapper

import com.example.demo.dto.MovieDTO
import com.example.demo.dto.PersonDTO
import com.example.demo.exceptions.EntityNotFoundException
import com.example.demo.model.Movie
import com.example.demo.model.MovieActor
import com.example.demo.model.Person
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList

@Component
class MovieMapper {
    private var modelMapper = ModelMapper()

    fun movieToDto(movie: Movie): MovieDTO {
        val movieDTO = MovieDTO()
        modelMapper.map(movie, movieDTO)
        movie.poster?.let { movieDTO.poster = Base64.getEncoder().encodeToString(it) }
        val size = movie.ratings.size.toDouble()
        movieDTO.avgRating = movie.ratings
                .map { it.value }
                .sum()
                .div(if (size == 0.0) 1.0 else size)
        movieDTO.totalRatings = size.toInt()
        movieDTO.director.role = "Director"
        movieDTO.actors = movie.actors.map(fun(it: MovieActor): PersonDTO {
            val personDto = PersonDTO()
            personDto.name = it.actor.name
            personDto.photo = it.actor.photo?.let { it1 -> String(it1) }
            personDto.id = it.actor.id
            personDto.role = it.role
            return personDto
        })
        return movieDTO
    }

    fun movieListToDtoList(movies: List<Movie>): List<MovieDTO> {
        return movies.map { movieToDto(it) }
    }

    fun dtoToMovie(movieDTO: MovieDTO, personList: List<Person>): Movie {
        val movie = Movie()
        modelMapper.map(movieDTO, movie)
        movieDTO.poster?.let { movie.poster = Base64.getDecoder().decode(it.toByteArray()) }
        movie.director = personList.findLast { person -> person.id == movie.director.id }
                ?: throw EntityNotFoundException("No such person with id " + movie.director.id, null)
        val movieActors = ArrayList<MovieActor>()
        for (actor in movieDTO.actors) {
            val movieActor = MovieActor()
            movieActor.actor = personList.findLast { person -> person.id == actor.id }
                    ?: throw EntityNotFoundException("No such person with id " + actor.id, null)
            movieActor.movie = movie
            movieActor.role = actor.role
            movieActors.add(movieActor)
        }
        movie.actors = movieActors
        return movie
    }
}