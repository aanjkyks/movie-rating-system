package com.example.demo.service

import com.example.demo.model.Person

interface PersonService {
    fun findAllPeople(): List<Person>
    fun savePerson(person: Person): Person
    fun findPeopleByName(name: String): List<Person>
    fun findPersonById(id: Long): Person
}