package com.example.demo.mapper;

import com.example.demo.dto.RatingDTO;
import com.example.demo.exceptions.MVREntityNotFoundException;
import com.example.demo.model.Movie;
import com.example.demo.model.Rating;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface RatingMapper {
    @NotNull
    RatingDTO ratingToDto(@NotNull Rating rating);

    @NotNull
    Rating dtoToRating(@NotNull RatingDTO ratingDTO, List<Movie> movies) throws MVREntityNotFoundException;
}
