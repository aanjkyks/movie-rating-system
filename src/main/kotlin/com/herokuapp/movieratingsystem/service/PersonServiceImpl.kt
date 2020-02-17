package com.herokuapp.movieratingsystem.service


import com.herokuapp.movieratingsystem.exceptions.MVREntityNotFoundException
import com.herokuapp.movieratingsystem.model.Person
import com.herokuapp.movieratingsystem.repository.PersonRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(private val personRepository: PersonRepository) : PersonService {
    override fun findAllPeople(): List<Person> {
        return personRepository.findAll()
    }

    override fun savePerson(person: Person): Person {
        return personRepository.save(person)
    }

    override fun findPeopleByName(name: String?, pageable: Pageable): Page<Person> {
        name?.let { return personRepository.findByNameContainingIgnoreCase(it, pageable) }
        return personRepository.findAll(pageable)
    }

    override fun findPersonById(id: Long): Person {
        return personRepository.findById(id).orElseThrow { MVREntityNotFoundException("no such person with id $id") }
    }

    override fun findPeopleBy(id: Long?, name: String?, pageable: Pageable): Page<Person> {
        val result = mutableListOf<Person>()
        id?.let { result.add(findPersonById(it)) }
        name?.let { result.addAll(findPeopleByName(it, pageable)) }
        if (result.isEmpty()) {
            result.addAll(personRepository.findAll())
        }
        return PageImpl<Person>(result, pageable, result.size.toLong())
    }
}