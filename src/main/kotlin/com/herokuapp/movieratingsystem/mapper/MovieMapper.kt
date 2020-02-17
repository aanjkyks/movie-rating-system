package com.herokuapp.movieratingsystem.mapper


import com.herokuapp.movieratingsystem.dto.MovieDto
import com.herokuapp.movieratingsystem.dto.PersonDTO
import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList

@Component
class MovieMapper {
    private val modelMapper = ModelMapper()

    fun singleMovieToDto(movie: Movie): MovieDto {
        val movieDTO = movieToBatchDto(movie)
        movie.director.photo?.let { movieDTO.director.photo = Base64.getEncoder().encodeToString(it) }
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

    fun movieListToDtoList(movies: Page<Movie>): Page<MovieDto> {
        return movies.map(this::movieToBatchDto)
    }

    fun movieToBatchDto(movie: Movie): MovieDto {
        val movieDTO = MovieDto()
        modelMapper.map(movie, movieDTO)
        movie.poster?.let { movieDTO.poster = Base64.getEncoder().encodeToString(it) }
        val size = movie.ratings.size.toDouble()
        movieDTO.totalRatings = size.toInt()
        movieDTO.director.role = "Director"
        movieDTO.director.photo = null
        movieDTO.actors = emptyList()
        return movieDTO
    }

    fun dtoToMovie(movieDto: MovieDto, personList: List<Person>): Movie {
        val movie = Movie()
        val sortedPersonList = personList.sortedBy { it.id }
        modelMapper.map(movieDto, movie)
        movieDto.poster?.let { movie.poster = Base64.getDecoder().decode(it.toByteArray()) }
        val foundDirectorIndex = sortedPersonList.binarySearchBy(movie.director.id) { it.id }
        movie.director = sortedPersonList[if (foundDirectorIndex > -1) foundDirectorIndex else throw throw MVREntityNotFoundException("No such person with id " + movie.director.id)]
        val movieActors = ArrayList<MovieActor>()
        for (actor in movieDto.actors) {
            val movieActor = MovieActor()
            val foundActorIndex = sortedPersonList.binarySearchBy(actor.id) { it.id }
            movieActor.actor = sortedPersonList[if (foundActorIndex > -1) foundActorIndex else throw throw MVREntityNotFoundException("No such person with id " + movie.director.id)]
            movieActor.movie = movie
            movieActor.role = actor.role
            movieActors.add(movieActor)
        }
        movie.actors = movieActors
        return movie
    }
}