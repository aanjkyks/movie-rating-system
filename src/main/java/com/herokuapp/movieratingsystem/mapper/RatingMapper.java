package com.herokuapp.movieratingsystem.mapper;

import com.herokuapp.movieratingsystem.dto.RatingDTO;
import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException;
import com.herokuapp.movieratingsystem.exceptions.MVRInternalErrorException;
import com.herokuapp.movieratingsystem.model.Movie;
import com.herokuapp.movieratingsystem.model.Rating;
import org.jetbrains.annotations.NotNull;

public interface RatingMapper {
    @NotNull
    RatingDTO ratingToDto(@NotNull Rating rating) throws MVRInternalErrorException;

    @NotNull
    Rating dtoToRating(@NotNull RatingDTO ratingDTO, Movie movie) throws MVREntityNotFoundException;
}
