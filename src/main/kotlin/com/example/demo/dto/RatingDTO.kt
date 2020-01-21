package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

class RatingDTO(
        var id: Long? = null,
        @JsonProperty("movie")
        var movieId: Long = 0,
        var value: Int = 0
)