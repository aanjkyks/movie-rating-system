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

class RatingDTO(
        var id: Long? = null,
        var movie: Long = 0,
        var value: Int = 0
)

class PersonDTO(
        var id: Long? = null,
        var name: String = "",
        var photo: String? = null,
        var role: String = ""
)

class ActorInfoDTO(
        var id : Long? = null,
        var name : String = "",
        var movies : List<MovieActorDto> = emptyList()
)

class MovieActorDto(
        var movie: Long? = null,
        var role : String = ""
)
