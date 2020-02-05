package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.PersonRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class PersonServiceImplTest {

    val personRepository: PersonRepository = mock()

    val personServiceImpl = PersonServiceImpl(personRepository)

    @Test
    fun findAllPeople() {
        personServiceImpl.findAllPeople()
        verify(personRepository, times(1)).findAll()
    }

    @Test
    fun savePerson() {
        whenever(personRepository.save(any<Person>())).thenReturn(Person())
        personServiceImpl.savePerson(Person())
        verify(personRepository, times(1)).save(any<Person>())
    }

    @Test
    fun findPeopleByName() {
        personServiceImpl.findPeopleByName("Person")
        verify(personRepository, times(1)).findByName("Person")
    }

    @Test
    internal fun findPeopleByNullName() {
        personServiceImpl.findPeopleByName(null)
        verify(personRepository, times(1)).findAll()
    }

    @Test
    fun findPersonByIdSuccess() {
        whenever(personRepository.findById(any())).thenReturn(Optional.of(Person()))
        personServiceImpl.findPersonById(1)
        verify(personRepository).findById(1)
    }

    @Test
    internal fun findPersonByIdThrows() {
        whenever(personRepository.findById(any())).thenReturn(Optional.empty())
        assertThrows<MVREntityNotFoundException> { personServiceImpl.findPersonById(1) }
    }

    @Test
    fun findPeopleByNullParams() {
        personServiceImpl.findPeopleBy(null, null)
        verify(personRepository, times(1)).findAll()
    }

    @Test
    internal fun findPeopleByNullNameParam() {
        whenever(personRepository.findById(1)).thenReturn(Optional.of(Person()))
        personServiceImpl.findPeopleBy(1, null)
        verify(personRepository, times(1)).findById(1)
        verify(personRepository, never()).findByName(any())
    }

    @Test
    internal fun findPeopleByNullIdParam() {
        personServiceImpl.findPeopleBy(null, "Name")
        verify(personRepository, never()).findById(any())
        verify(personRepository, times(1)).findByName("Name")
    }
}