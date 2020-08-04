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
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository,
                       private val ratingRepository: RatingRepository,
                       private val personRepository: PersonRepository,
                       private val movieActorRepository: MovieActorRepository) : MovieService {

    @Transactional
    override fun saveMovie(movie: Movie): Movie {
        movie.id?.let {
            val ratings = ratingRepository.findByMovie(movie)
            movie.ratings = ratings
            movie.avgRating = ratings.map { it.value }
                    .sum()
                    .div(ratings.size.toDouble())
            movieActorRepository.deleteByMovie(movie)
        }
        if (movie.poster == null) {
            movie.poster = movie.id?.let { movieRepository.findById(it).orElse(Movie()).poster }
        }
        return movieRepository.save(movie)
    }

    override fun findMovies(name: String?, dName: String?, pageable: Pageable): Page<Movie> {

        dName?.let { directorName ->
            val movies = mutableSetOf<Movie>()
            val directors = personRepository.findByNameContainingIgnoreCase(directorName, Pageable.unpaged())
            movies.addAll(movieRepository.findByDirectorIn(directors.content))
            name?.let { movies.addAll(movieRepository.findByNameContainingIgnoreCase(name, Pageable.unpaged()).filter { directors.contains(it.director) }) }
            return PageImpl<Movie>(movies.toList(), pageable, movies.size.toLong())
        }
        name?.let { return movieRepository.findByNameContainingIgnoreCase(it, pageable) }
        return movieRepository.findAll(pageable)
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