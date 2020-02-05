package com.herokuapp.movieratingsystem.mapper

import com.herokuapp.movieratingsystem.dto.PersonDTO
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.utils.MovieTestingUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PersonMapperTest {

    val personMapper: PersonMapper = spy()

    @Test
    fun personToDto() {
        val person = MovieTestingUtils.createPerson()
        val expected = PersonDTO(id = 1, name = "Person name")
        assertEquals(expected, personMapper.personToDto(person))
    }

    @Test
    fun peopleListToDtos() {
        val people = List(10) { Person(id = it.toLong()) }
        personMapper.peopleListToDtos(people)
        verify(personMapper, times(people.size)).personToDto(any())
    }

    @Test
    fun dtoToPerson() {
        val personDTO = PersonDTO(id = 1, name = "Person")
        val expected = Person(id = 1, name = "Person")
        assertEquals(expected, personMapper.dtoToPerson(personDTO))
    }

    @Test
    fun peopleDtoListToPersonList() {
        val peopleDtos = List(10) { PersonDTO(id = it.toLong()) }
        personMapper.peopleDtoListToPersonList(peopleDtos)
        verify(personMapper, times(peopleDtos.size)).dtoToPerson(any())
    }
}