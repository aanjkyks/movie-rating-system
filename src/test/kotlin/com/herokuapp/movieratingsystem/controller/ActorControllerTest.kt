package com.herokuapp.movieratingsystem.controller

import com.herokuapp.movieratingsystem.dto.MoviePersonInfo
import com.herokuapp.movieratingsystem.mapper.ActorInfoMapper
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.MovieActorRepository
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.PersonService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ActorControllerTest {

    val movieService: MovieService = mock()
    val personService: PersonService = mock()
    val actorInfoMapper: ActorInfoMapper = mock()
    val movieActorRepository: MovieActorRepository = mock()

    val actorController = ActorController(movieService, personService, actorInfoMapper, movieActorRepository)

    @Test
    fun getActorInfo() {
        assertNotNull(actorController.getActorInfo(1L))
    }

    @BeforeEach
    internal fun setUp() {
        whenever(actorInfoMapper.actorListToInfoList(any(), any(), any())).thenReturn(MoviePersonInfo())
        whenever(personService.findPersonById(1L)).thenReturn(Person())
    }
}