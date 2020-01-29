package com.herokuapp.movieratingsystem.service


import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Movie
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.MovieActorRepository
import com.herokuapp.movieratingsystem.repository.MovieRepository
import com.herokuapp.movieratingsystem.repository.PersonRepository
import com.herokuapp.movieratingsystem.repository.RatingRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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

    override fun findAllPaginated(page: Int, size: Int): Page<Movie> {
        return movieRepository.findAll(PageRequest.of(page, size))
    }
}