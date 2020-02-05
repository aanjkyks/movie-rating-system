package com.herokuapp.movieratingsystem.mapper;

import com.herokuapp.movieratingsystem.dto.RatingDTO;
import com.herokuapp.movieratingsystem.model.Movie;
import com.herokuapp.movieratingsystem.model.Rating;
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RatingMapperJavaTest {

    RatingMapperJava ratingMapperJava = new RatingMapperJava();

    @Test
    public void ratingToDto() {
        Rating rating = new Rating();
        rating.setId(1L);
        rating.setValue(10);
        Movie movie = MovieTestingUtils.createMovie();
        rating.setMovie(movie);

        RatingDTO expected = new RatingDTO();
        expected.setValue(10);
        expected.setMovieId(rating.getMovie().getId());

        assertEquals(expected, ratingMapperJava.ratingToDto(rating));
    }

    @Test
    public void dtoToRating() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setMovieId(1L);
        ratingDTO.setValue(10);

        Rating expected = new Rating();
        Movie movie = MovieTestingUtils.createMovie();
        expected.setMovie(movie);
        expected.setValue(10);

        assertEquals(expected, ratingMapperJava.dtoToRating(ratingDTO, movie));
    }
}