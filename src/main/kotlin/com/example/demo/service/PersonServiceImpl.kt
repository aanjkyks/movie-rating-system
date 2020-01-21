package com.example.demo.service

import com.example.demo.exceptions.MVREntityNotFoundException
import com.example.demo.model.Person
import com.example.demo.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(private val personRepository: PersonRepository) : PersonService {
    override fun findAllPeople(): List<Person> {
        return personRepository.findAll()
    }

    override fun savePerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun findPeopleByName(name: String): List<Person> {
        return personRepository.findAll().filter { it.name.contains(name) }
    }

    override fun findPersonById(id: Long): Person {
        return personRepository.findById(id).orElseThrow { MVREntityNotFoundException("no such person with id $id") }
    }
}