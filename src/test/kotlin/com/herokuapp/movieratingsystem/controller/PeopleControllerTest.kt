package com.herokuapp.movieratingsystem.controller

import com.herokuapp.movieratingsystem.dto.PersonDTO
import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.mapper.PersonMapper
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.service.PersonService
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

internal class PeopleControllerTest {

    val peopleService: PersonService = mock()
    val personMapper: PersonMapper = mock()

    val peopleController = PeopleController(peopleService, personMapper)

    @Test
    fun createPerson() {
        whenever(personMapper.dtoToPerson(any())).thenReturn(Person())
        peopleController.createPerson(PersonDTO())
        verify(peopleService, times(1)).savePerson(any())
    }

    @Test
    fun createPersonWithId() {
        whenever(personMapper.dtoToPerson(any())).thenReturn(Person())
        val personDto = PersonDTO()
        personDto.id = 1
        assertThrows<MVRInvalidArgumentException> { peopleController.createPerson(personDto) }
        verify(peopleService, never()).savePerson(any())
    }

    @Test
    fun updatePerson() {
        whenever(personMapper.dtoToPerson(any())).thenReturn(Person())
        val personDTO = PersonDTO()
        personDTO.id = 1
        peopleController.updatePerson(personDTO)
        verify(peopleService, times(1)).savePerson(any())
    }

    @Test
    fun updatePersonNoId() {
        assertThrows<MVRInvalidArgumentException> { peopleController.updatePerson(PersonDTO()) }
        verify(peopleService, never()).savePerson(any())
    }

    @Test
    fun findPeople() {
        whenever(peopleService.findPeopleByName(any(), any())).thenReturn(PageImpl(listOf(Person())))
        peopleController.findPeople("name", Pageable.unpaged())
        verify(peopleService, times(1)).findPeopleByName(any(), any())
    }

    @Test
    fun findPerson() {
        whenever(peopleService.findPersonById(any())).thenReturn(Person())
        peopleController.findPerson(1)
        verify(peopleService, times(1)).findPersonById(any())
    }
}