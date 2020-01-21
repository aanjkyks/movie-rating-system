package com.example.demo.mapper;

import com.example.demo.dto.RatingDTO;
import com.example.demo.exceptions.MVREntityNotFoundException;
import com.example.demo.model.Movie;
import com.example.demo.model.Rating;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class RatingMapperJava implements RatingMapper {
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @NotNull
    public RatingDTO ratingToDto(@NotNull Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        modelMapper.map(rating, ratingDTO);
        ratingDTO.setMovieId(Optional.ofNullable(rating.getMovie().getId())
                .orElseThrow(() -> new IllegalStateException("Movie has no id " + rating.getMovie().getName())));
        return ratingDTO;
    }

    @Override
    @NotNull
    public Rating dtoToRating(@NotNull RatingDTO ratingDTO, List<Movie> movies) throws MVREntityNotFoundException {
        Rating rating = new Rating();
        modelMapper.map(ratingDTO, rating);
        rating.setMovie(movies.stream()
                .filter(movie -> Objects.equals(movie.getId(), ratingDTO.getMovieId()))
                .findFirst()
                .orElseThrow(() -> new MVREntityNotFoundException("No such movie with id " + ratingDTO.getMovieId())));
        return rating;
    }
}
