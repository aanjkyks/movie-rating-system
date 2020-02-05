package com.herokuapp.movieratingsystem.mapper

import com.herokuapp.movieratingsystem.dto.MoviePersonDto
import com.herokuapp.movieratingsystem.dto.MoviePersonInfo
import com.herokuapp.movieratingsystem.model.MovieActor
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ActorInfoMapperTest {

    val actorInfoMapper = ActorInfoMapper()

    @Test
    fun actorListToInfoList() {
        val person = MovieTestingUtils.createPerson()

        val directed = MovieTestingUtils.createMovie()
        directed.director = person
        directed.name = "Directed Movie"
        directed.id = 100

        val starred = MovieTestingUtils.createMovie()
        starred.name = "Starred Movie"

        val movieActor = MovieActor(actor = person, movie = starred, role = "Main Role")
        starred.actors = listOf(movieActor)

        val directedInfo = MoviePersonDto(movie = 100, movieName = "Directed Movie", role = "Director")

        val starredInfo = MoviePersonDto(movie = 1, movieName = "Starred Movie", role = "Main Role")

        val moviePersonInfo = MoviePersonInfo(id = person.id, name = person.name, movies = listOf(starredInfo, directedInfo))

        assertEquals(moviePersonInfo, actorInfoMapper.actorListToInfoList(person, listOf(movieActor), listOf(directed)))
    }
}