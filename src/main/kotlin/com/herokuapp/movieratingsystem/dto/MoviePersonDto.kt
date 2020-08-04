package com.herokuapp.movieratingsystem.dto

class MoviePersonDto(
        var movie: Long? = null,
        var movieName: String = "",
        var role: String = "",
        var poster: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MoviePersonDto

        if (movie != other.movie) return false
        if (movieName != other.movieName) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = movie?.hashCode() ?: 0
        result = 31 * result + movieName.hashCode()
        result = 31 * result + role.hashCode()
        return result
    }

    override fun toString(): String {
        return "MoviePersonDto(movie=$movie, movieName='$movieName', role='$role')"
    }
}
