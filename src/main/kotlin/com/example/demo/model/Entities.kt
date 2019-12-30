package com.example.demo.model

import java.io.Serializable
import javax.persistence.*

@Entity
class Rating(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @ManyToOne @JoinColumn(name = "movie_id")
        var movie: Movie = Movie(),
        var value: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rating

        if (id != other.id) return false
        if (movie != other.movie) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + movie.hashCode()
        result = 31 * result + value
        return result
    }
}

@Entity
class Movie(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL])
        var ratings: List<Rating> = emptyList(),
        var description: String? = null,
        var name: String = "",
        var poster: Array<Byte>? = null,
        @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.DETACH])
        var director: Person = Person(),
        @OneToMany(mappedBy = "pk.movie", cascade = [CascadeType.PERSIST, CascadeType.DETACH])
        var actors: List<MovieActor> = emptyList(),
        var averageRating: Double = 0.0

) {
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

@Entity
class Person(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String = "",
        var photo: Array<Byte>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false
        if (name != other.name) return false
        if (photo != null) {
            if (other.photo == null) return false
            if (!photo!!.contentEquals(other.photo!!)) return false
        } else if (other.photo != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + (photo?.contentHashCode() ?: 0)
        return result
    }
}

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
