package com.herokuapp.movieratingsystem.dto

class MovieDto(
        var id: Long? = null,
        var avgRating: Double = 0.0,
        var totalRatings: Int = 0,
        var description: String? = null,
        var name: String = "",
        var poster: String? = null,
        var director: PersonDTO = PersonDTO(),
        var actors: List<PersonDTO> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDto

        if (id != other.id) return false
        if (avgRating != other.avgRating) return false
        if (totalRatings != other.totalRatings) return false
        if (description != other.description) return false
        if (name != other.name) return false
        if (poster != other.poster) return false
        if (director != other.director) return false
        if (actors != other.actors) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + avgRating.hashCode()
        result = 31 * result + totalRatings
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + (poster?.hashCode() ?: 0)
        result = 31 * result + director.hashCode()
        result = 31 * result + actors.hashCode()
        return result
    }

    override fun toString(): String {
        return "MovieDto(id=$id, avgRating=$avgRating, totalRatings=$totalRatings, description=$description, name='$name', poster=$poster, director=$director, actors=$actors)"
    }
}