package com.herokuapp.movieratingsystem.dto

class SingleMovieDTO(
        var id: Long? = null,
        var avgRating: Double = 0.0,
        var totalRatings: Int = 0,
        var description: String? = null,
        var name: String = "",
        var poster: String? = null,
        var director: PersonDTO = PersonDTO(),
        var actors: List<PersonDTO> = emptyList()
)