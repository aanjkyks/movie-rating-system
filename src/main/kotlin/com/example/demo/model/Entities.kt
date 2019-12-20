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
)

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
        @OneToMany(mappedBy = "pk.movie", cascade = [CascadeType.ALL])
        var actors: List<MovieActor> = emptyList()

)

@Entity
class Person(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String = "",
        var surname: String = "",
        var photo: Array<Byte>? = null
)

@Entity
class MovieActor(
        @EmbeddedId
        var pk: MovieActorPK = MovieActorPK(),
        var role: String = ""
)

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
