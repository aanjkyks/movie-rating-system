package com.example.demo.service

import com.example.demo.model.Movie
import com.example.demo.repository.MovieActorRepository
import com.example.demo.repository.MovieRepository
import com.example.demo.repository.PersonRepository
import com.example.demo.repository.RatingRepository
import org.springframework.stereotype.Component

interface IMovieService {
    fun saveMovie(movie: Movie): Movie
    fun findMovies(name: String?, dName: String?): List<Movie>
}

@Component
class MovieServiceImpl(val movieRepository: MovieRepository,
                       val ratingRepository: RatingRepository,
                       val personRepository: PersonRepository,
                       val movieActorRepository: MovieActorRepository) : IMovieService {
    override fun saveMovie(movie: Movie): Movie {
        movie.id?.let {
            movie.ratings = ratingRepository.findByMovie(movie)
        }
        return movieRepository.save(movie)
    }

    override fun findMovies(name: String?, dName: String?): List<Movie> {
        if (name == null && dName == null) {
            return movieRepository.findAll()
        }

        dName?.let { directorName ->
            val movies = mutableListOf<Movie>()
            val directors = personRepository.findByName(directorName)
            for (director in directors) {
                movies.addAll(movieRepository.findByDirector(director))
            }
            name?.let { movies.addAll(movieRepository.findByNameContaining(name).filter { directors.contains(it.director) }) }
            return movies.distinct()
        }
        return movieRepository.findByNameContaining(name)
    }
}