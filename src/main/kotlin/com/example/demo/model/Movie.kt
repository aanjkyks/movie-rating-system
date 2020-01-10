package com.example.demo.model

import java.io.Serializable
import javax.persistence.*

@Entity
class Movie(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL])
        var ratings: List<Rating> = emptyList(),
        var description: String? = null,
        var name: String = "",
        var poster: Array<Byte>? = null,
        @ManyToOne(cascade = [CascadeType.DETACH])
        var director: Person = Person(),
        @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL])
        var actors: List<MovieActor> = emptyList(),
        var averageRating: Double = 0.0

) : Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (ratings != other.ratings) return false
        if (description != other.description) return false
        if (name != other.name) return false
        if (poster != null) {
            if (other.poster == null) return false
            if (!poster!!.contentEquals(other.poster!!)) return false
        } else if (other.poster != null) return false
        if (director != other.director) return false
        if (actors != other.actors) return false
        if (averageRating != other.averageRating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + ratings.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + (poster?.contentHashCode() ?: 0)
        result = 31 * result + director.hashCode()
        result = 31 * result + actors.hashCode()
        result = 31 * result + averageRating.hashCode()
        return result
    }
}