package com.example.demo.mapper;

import com.example.demo.dto.RatingDTO;
import com.example.demo.exceptions.EntityNotFoundException;
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
//        modelMapper.typeMap(Rating.class, RatingDTO.class)
//                .addMappings(mapping -> mapping.map(source -> source.getMovie().getId(), RatingDTO::setMovie));
        modelMapper.map(rating, ratingDTO);
        ratingDTO.setMovieId(Optional.ofNullable(rating.getMovie().getId())
                .orElseThrow(() -> new IllegalStateException("Movie has no id " + rating.getMovie().getName())));
        return ratingDTO;
    }

    @Override
    @NotNull
    public Rating dtoToRating(@NotNull RatingDTO ratingDTO, List<Movie> movies) throws EntityNotFoundException {
        Rating rating = new Rating();
//        modelMapper.typeMap(RatingDTO.class, Rating.class)
//                .addMappings(mapping -> mapping.skip(Rating::setMovie));
        modelMapper.map(ratingDTO, rating);
        rating.setMovie(movies.stream()
                .filter(movie -> Objects.equals(movie.getId(), ratingDTO.getMovieId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No such movie with id " + ratingDTO.getMovieId())));
        return rating;
    }
}
