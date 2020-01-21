package com.example.demo.mapper

import com.example.demo.dto.MoviePersonDto
import com.example.demo.dto.MoviePersonInfo
import com.example.demo.model.Movie
import com.example.demo.model.MovieActor
import com.example.demo.model.Person
import org.springframework.stereotype.Component

@Component
class ActorInfoMapper {
    fun actorListToInfoList(person: Person, movieActors: List<MovieActor>, movies: List<Movie>): MoviePersonInfo {
        return toActorInfoDto(person, movieActors, movies)
    }

    private fun toActorInfoDto(person: Person, movieActors: List<MovieActor>, movies: List<Movie>): MoviePersonInfo {
        val actorInfo = MoviePersonInfo()
        actorInfo.id = person.id
        actorInfo.name = person.name
        actorInfo.movies = movieActors
                .filter { it.actor == person }
                .map { movieActorToDto(it) }
        (actorInfo.movies as MutableList).addAll(movies.filter { it.director == person }.map { movieToMoviePersonDto(it) })
        return actorInfo
    }

    private fun movieActorToDto(movieActor: MovieActor): MoviePersonDto {
        val moviePersonDto = MoviePersonDto()
        moviePersonDto.movie = movieActor.movie.id
        moviePersonDto.movieName = movieActor.movie.name
        moviePersonDto.role = movieActor.role
        return moviePersonDto
    }

    private fun movieToMoviePersonDto(movie: Movie): MoviePersonDto {
        val moviePersonDto = MoviePersonDto()
        moviePersonDto.movie = movie.id
        moviePersonDto.movieName = movie.name
        moviePersonDto.role = "Director"
        return moviePersonDto
    }
}