package com.herokuapp.movieratingsystem.model

import javax.persistence.*

@Entity
class Rating(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @ManyToOne
        @JoinColumn(name = "movie_id")
        var movie: Movie = Movie(),
        var value: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rating

        if (id != other.id) return false
        if (movie.id != other.movie.id) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + movie.hashCode()
        result = 31 * result + value
        return result
    }

    override fun toString(): String {
        return "Rating(id=$id, movie.id=${movie.id}, value=$value)"
    }
}