package com.example.demo.model

import java.io.Serializable
import javax.persistence.ManyToOne

class MovieActorPK(
        @ManyToOne
        var movie: Movie = Movie(),
        @ManyToOne
        var actor: Person = Person()
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieActorPK

        if (movie != other.movie) return false
        if (actor != other.actor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = movie.hashCode()
        result = 31 * result + actor.hashCode()
        return result
    }
}
