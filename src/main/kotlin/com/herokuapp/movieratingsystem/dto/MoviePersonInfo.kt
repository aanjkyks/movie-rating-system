package com.herokuapp.movieratingsystem.dto

class MoviePersonInfo(
        var id: Long? = null,
        var name: String = "",
        var photo: String? = null,
        var movies: List<MoviePersonDto> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MoviePersonInfo

        if (id != other.id) return false
        if (name != other.name) return false
        if (photo != other.photo) return false
        if (movies != other.movies) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + (photo?.hashCode() ?: 0)
        result = 31 * result + movies.hashCode()
        return result
    }

    override fun toString(): String {
        return "MoviePersonInfo(id=$id, name='$name', photo=$photo, movies=$movies)"
    }
}