package com.example.demo.dto

class MovieDTO(
        var id: Long? = null,
        var avgRating: Double = 0.0,
        var totalRatings: Int = 0,
        var description: String? = null,
        var name: String = "",
        var poster: Array<Byte>? = null,
        var director: PersonDTO = PersonDTO(),
        var actors: List<PersonDTO> = emptyList()
)

class RatingDTO(
        var id: Long? = null,
        var movie: Long = 0,
        var value: Int = 0
)

class PersonDTO(
        var id: Long? = null,
        var name: String = "",
        var surname: String = "",
        var photo: Array<Byte>? = null
)
