package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.PersonRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
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
        personServiceImpl.findPeopleByName("Person", Pageable.unpaged())
        verify(personRepository, times(1)).findByNameContainingIgnoreCase(any(), any())
    }

    @Test
    internal fun findPeopleByNullName() {
        whenever(personRepository.findAll(any<Pageable>())).thenReturn(PageImpl(listOf(Person())))
        personServiceImpl.findPeopleByName(null, Pageable.unpaged())
        verify(personRepository, times(1)).findAll(any<Pageable>())
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
        personServiceImpl.findPeopleBy(null, null, Pageable.unpaged())
        verify(personRepository, times(1)).findAll()
    }

    @Test
    internal fun findPeopleByNullNameParam() {
        whenever(personRepository.findById(1)).thenReturn(Optional.of(Person()))
        personServiceImpl.findPeopleBy(1, null, Pageable.unpaged())
        verify(personRepository, times(1)).findById(1)
        verify(personRepository, never()).findByNameContainingIgnoreCase(any(), any())
    }

    @Test
    internal fun findPeopleByNullIdParam() {
        whenever(personRepository.findByNameContainingIgnoreCase(any(), any())).thenReturn(PageImpl(listOf(Person())))
        personServiceImpl.findPeopleBy(null, "Name", Pageable.unpaged())
        verify(personRepository, never()).findById(any())
        verify(personRepository, times(1)).findByNameContainingIgnoreCase(any(), any())
    }
}