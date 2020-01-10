package com.example.demo.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class MovieActor(
        @EmbeddedId
        var pk: MovieActorPK = MovieActorPK(),
        var role: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieActor

        if (pk != other.pk) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pk.hashCode()
        result = 31 * result + role.hashCode()
        return result
    }
}