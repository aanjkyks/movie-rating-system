package com.example.demo.dto

class MovieDTO(
        var id: Long? = null,
        var avgRating: Double = 0.0,
        var totalRatings: Int = 0,
        var description: String? = null,
        var name: String = "",
        var poster: String? = null,
        var director: PersonDTO = PersonDTO(),
        var actors: List<PersonDTO> = emptyList()
)