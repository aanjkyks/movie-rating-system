package com.herokuapp.movieratingsystem.dto

import com.fasterxml.jackson.annotation.JsonProperty

class RatingDTO(
        @JsonProperty("movie")
        var movieId: Long = 0,
        var value: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RatingDTO

        if (movieId != other.movieId) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = movieId.hashCode()
        result = 31 * result + value
        return result
    }

    override fun toString(): String {
        return "RatingDTO(movieId=$movieId, value=$value)"
    }
}