package com.herokuapp.movieratingsystem.service


import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.MovieActorRepository
import com.herokuapp.movieratingsystem.repository.MovieRepository
import com.herokuapp.movieratingsystem.repository.PersonRepository
import com.herokuapp.movieratingsystem.repository.RatingRepository
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository,
                       private val ratingRepository: RatingRepository,
                       private val personRepository: PersonRepository,
                       private val movieActorRepository: MovieActorRepository) : MovieService {
    override fun saveMovie(movie: Movie): Movie {
        movie.id?.let {
            movie.ratings = ratingRepository.findByMovie(movie)
        }
        return movieRepository.save(movie)
    }

    override fun findMovies(name: String?, dName: String?): List<Movie> {

        dName?.let { directorName ->
            val movies = mutableSetOf<Movie>()
            val directors = personRepository.findByNameContainingIgnoreCase(directorName)
            for (director in directors) {
                movies.addAll(movieRepository.findByDirector(director))
            }
            name?.let { movies.addAll(movieRepository.findByNameContainingIgnoreCase(name).filter { directors.contains(it.director) }) }
            return movies.toList()
        }
        name?.let { return movieRepository.findByNameContainingIgnoreCase(it) }
        return movieRepository.findAll()
    }

    override fun findAllMovies(): List<Movie> {
        return movieRepository.findAll()
    }

    override fun findAllMovieActors(): List<MovieActor> {
        return movieActorRepository.findAll()
    }

    override fun findByDirector(director: Person): List<Movie> {
        return movieRepository.findByDirector(director)
    }

    override fun findById(id: Long): Movie {
        return movieRepository.findById(id).orElseThrow { MVREntityNotFoundException("No such movie with id $id") }
    }
}