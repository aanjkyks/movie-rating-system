package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.model.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface PersonService {
    fun findAllPeople(): List<Person>
    fun savePerson(person: Person): Person
    fun findPeopleByName(name: String?, pageable: Pageable = Pageable.unpaged()): Page<Person>
    fun findPersonById(id: Long): Person
    fun findPeopleBy(id: Long?, name: String?, pageable: Pageable): Page<Person>
}