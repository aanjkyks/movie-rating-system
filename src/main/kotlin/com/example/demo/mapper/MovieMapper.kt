package com.example.demo.mapper

import com.example.demo.dto.MovieDTO
import com.example.demo.model.Movie
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class MovieMapper {
    private var modelMapper = ModelMapper()

    fun movieToDto(movie: Movie): MovieDTO {
        val movieDTO = MovieDTO()
        modelMapper.map(movie, movieDTO)
        val size = movie.ratings.size.toDouble()
        movieDTO.avgRating = movie.ratings.stream()
                .map { rating -> rating.value }
                .collect(Collectors.toList())
                .sum()
                .div(if (size == 0.0) 1.0 else size)
        movieDTO.totalRatings = size.toInt()
        return movieDTO
    }

    fun movieListTodtoList(movies: List<Movie>): List<MovieDTO> {
        return movies.map { movie -> movieToDto(movie) }
    }

    fun dtoToMovie(movieDTO: MovieDTO):Movie{
        val movie = Movie()
        modelMapper.map(movieDTO, movie)
        return movie
    }
}