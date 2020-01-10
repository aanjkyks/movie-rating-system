package com.example.demo.model

import javax.persistence.*

@Entity
class MovieActor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @ManyToOne
        var movie: Movie = Movie(),

        @ManyToOne
        var actor: Person = Person(),
        var role: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieActor

        if (id != other.id) return false
        if (movie != other.movie) return false
        if (actor != other.actor) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + movie.hashCode()
        result = 31 * result + actor.hashCode()
        result = 31 * result + role.hashCode()
        return result
    }
}