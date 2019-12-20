package com.example.demo.startup

import com.example.demo.model.Movie
import com.example.demo.repository.MovieActorRepository
import com.example.demo.repository.MovieRepository
import com.example.demo.repository.PersonRepository
import com.example.demo.repository.RatingRepository
import org.springframework.boot.CommandLineRunner

class Startup(
        var ratingRepository: RatingRepository,
        var movieRepository: MovieRepository,
        var personRepository: PersonRepository,
        var movieActorRepository: MovieActorRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {

    }
}