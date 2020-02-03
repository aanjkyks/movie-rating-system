package com.herokuapp.movieratingsystem.mapper;

import com.herokuapp.movieratingsystem.dto.RatingDTO;
import com.herokuapp.movieratingsystem.exceptions.MVRInternalErrorException;
import com.herokuapp.movieratingsystem.model.Movie;
import com.herokuapp.movieratingsystem.model.Rating;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RatingMapperJava implements RatingMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    @NotNull
    public RatingDTO ratingToDto(@NotNull Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        modelMapper.map(rating, ratingDTO);
        ratingDTO.setMovieId(Optional.ofNullable(rating.getMovie().getId())
                .orElseThrow(() -> new MVRInternalErrorException("Movie has no id " + rating.getMovie().getName())));
        return ratingDTO;
    }

    @Override
    @NotNull
    public Rating dtoToRating(@NotNull RatingDTO ratingDTO, Movie movie) {
        Rating rating = new Rating();
        rating.setValue(ratingDTO.getValue());
        rating.setMovie(movie);
        return rating;
    }
}
