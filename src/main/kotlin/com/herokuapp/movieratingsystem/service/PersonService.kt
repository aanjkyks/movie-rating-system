package com.herokuapp.movieratingsystem.service

import com.herokuapp.movieratingsystem.model.Person


interface PersonService {
    fun findAllPeople(): List<Person>
    fun savePerson(person: Person): Person
    fun findPeopleByName(name: String?): List<Person>
    fun findPersonById(id: Long): Person
    fun findPeopleBy(id: Long?, name: String?): List<Person>
}