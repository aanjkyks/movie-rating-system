package com.herokuapp.movieratingsystem.mapper


import com.herokuapp.movieratingsystem.dto.PersonDTO
import com.herokuapp.movieratingsystem.dto.SingleMovieDTO
import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList

@Component
class MovieMapper {
    private var modelMapper = ModelMapper()

    fun singleMovieToDto(movie: Movie): SingleMovieDTO {
        val movieDTO = SingleMovieDTO()
        modelMapper.map(movie, movieDTO)
        movie.poster?.let { movieDTO.poster = Base64.getEncoder().encodeToString(it) }
        val size = movie.ratings.size.toDouble()
        movieDTO.avgRating = movie.ratings
                .map { it.value }
                .sum()
                .div(if (size == 0.0) 1.0 else size)
        movieDTO.totalRatings = size.toInt()
        movieDTO.director.role = "Director"
        movieDTO.actors = movie.actors.map(fun(movieActor: MovieActor): PersonDTO {
            val personDto = PersonDTO()
            personDto.name = movieActor.actor.name
            personDto.photo = movieActor.actor.photo?.let { photoBytes -> Base64.getEncoder().encodeToString(photoBytes) }
            personDto.id = movieActor.actor.id
            personDto.role = movieActor.role
            return personDto
        })
        return movieDTO
    }

    fun movieListToDtoList(movies: List<Movie>): List<SingleMovieDTO> {
        return movies.map(this::movieToBatchDto)
    }

    fun movieToBatchDto(movie: Movie): SingleMovieDTO {
        val batchDto = singleMovieToDto(movie)
        batchDto.actors = emptyList()
        batchDto.director.photo = null
        return batchDto
    }

    fun dtoToMovie(singleMovieDTO: SingleMovieDTO, personList: List<Person>): Movie {
        val movie = Movie()
        modelMapper.map(singleMovieDTO, movie)
        singleMovieDTO.poster?.let { movie.poster = Base64.getDecoder().decode(it.toByteArray()) }
        movie.director = personList.findLast { it.id == movie.director.id }
                ?: throw MVREntityNotFoundException("No such person with id " + movie.director.id)
        val movieActors = ArrayList<MovieActor>()
        for (actor in singleMovieDTO.actors) {
            val movieActor = MovieActor()
            movieActor.actor = personList.findLast { it.id == actor.id }
                    ?: throw MVREntityNotFoundException("No such person with id " + actor.id)
            movieActor.movie = movie
            movieActor.role = actor.role
            movieActors.add(movieActor)
        }
        movie.actors = movieActors
        return movie
    }
}