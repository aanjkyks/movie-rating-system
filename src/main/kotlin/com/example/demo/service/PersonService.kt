package com.example.demo.service

import com.example.demo.model.Person
import com.example.demo.repository.PersonRepository
import org.springframework.stereotype.Service

interface IPersonService {
    fun findAllPeople(): List<Person>
    fun savePerson(person: Person): Person
    fun findPeopleByName(name:String) : List<Person>
}

@Service
class PersonService(private val personRepository: PersonRepository) : IPersonService {
    override fun findAllPeople(): List<Person> {
        return personRepository.findAll()
    }

    override fun savePerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun findPeopleByName(name: String): List<Person> {
        return personRepository.findAll().filter { it.name.contains(name) }
    }
}