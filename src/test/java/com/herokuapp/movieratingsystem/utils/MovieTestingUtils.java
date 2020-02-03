package com.herokuapp.movieratingsystem.utils;

import com.herokuapp.movieratingsystem.model.Movie;
import com.herokuapp.movieratingsystem.model.MovieActor;
import com.herokuapp.movieratingsystem.model.Person;

import java.util.List;

public class MovieTestingUtils {
    public static Movie createMovie() {
        Movie movie = new Movie();
        movie.setDescription("desc");

        Person director = new Person();
        director.setId(1L);
        director.setName("Director");
        movie.setDirector(director);

        movie.setId(1L);
        movie.setName("Movie Name");

        MovieActor movieActor = new MovieActor();
        movieActor.setId(1L);
        movieActor.setMovie(movie);
        Person actor = new Person();
        actor.setName("Actor");
        actor.setId(2L);
        movieActor.setActor(actor);

        movie.setActors(List.of(movieActor));
        return movie;
    }
}
