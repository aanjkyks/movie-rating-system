package com.herokuapp.movieratingsystem.service


import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(private val personRepository: PersonRepository) : PersonService {
    override fun findAllPeople(): List<Person> {
        return personRepository.findAll()
    }

    override fun savePerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun findPeopleByName(name: String?): List<Person> {
        name?.let { return personRepository.findByName(it) }
        return personRepository.findAll()
    }

    override fun findPersonById(id: Long): Person {
        return personRepository.findById(id).orElseThrow { MVREntityNotFoundException("no such person with id $id") }
    }

    override fun findPeopleBy(id: Long?, name: String?): List<Person> {
        val result = mutableListOf<Person>()
        id?.let { result.add(findPersonById(it)) }
        name?.let { result.addAll(findPeopleByName(it)) }
        if (result.isEmpty()) {
            result.addAll(personRepository.findAll())
        }
        return result
    }
}