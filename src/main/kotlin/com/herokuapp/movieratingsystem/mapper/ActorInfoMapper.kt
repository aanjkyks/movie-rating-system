package com.herokuapp.movieratingsystem.mapper

import com.herokuapp.movieratingsystem.dto.MoviePersonDto
import com.herokuapp.movieratingsystem.dto.MoviePersonInfo
import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import org.springframework.stereotype.Component
import java.util.*


@Component
class ActorInfoMapper {
    fun actorListToInfoList(person: Person, movieActors: List<MovieActor>, movies: List<Movie>): MoviePersonInfo {
        return toActorInfoDto(person, movieActors, movies)
    }

    private fun toActorInfoDto(person: Person, movieActors: List<MovieActor>, moviesDirected: List<Movie>): MoviePersonInfo {
        val actorInfo = MoviePersonInfo()
        actorInfo.id = person.id
        person.photo?.let { actorInfo.photo = Base64.getEncoder().encodeToString(it) }
        actorInfo.name = person.name
        actorInfo.movies = movieActors.map { movieActorToDto(it) }
        (actorInfo.movies as MutableList).addAll(moviesDirected.map { movieToMoviePersonDtoDirector(it) })
        return actorInfo
    }

    private fun movieActorToDto(movieActor: MovieActor): MoviePersonDto {
        val moviePersonDto = MoviePersonDto()
        moviePersonDto.movie = movieActor.movie.id
        moviePersonDto.movieName = movieActor.movie.name
        moviePersonDto.role = movieActor.role
        moviePersonDto.poster = Base64.getEncoder().encodeToString(movieActor.movie.poster)
        return moviePersonDto
    }

    private fun movieToMoviePersonDtoDirector(movie: Movie): MoviePersonDto {
        val moviePersonDto = MoviePersonDto()
        moviePersonDto.movie = movie.id
        moviePersonDto.movieName = movie.name
        moviePersonDto.role = "Director"
        moviePersonDto.poster = Base64.getEncoder().encodeToString(movie.poster)
        return moviePersonDto
    }
}