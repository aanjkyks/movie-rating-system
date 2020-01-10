package com.example.demo.mapper

import com.example.demo.dto.PersonDTO
import com.example.demo.model.Person
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
class PersonMapper {
    private var modelMapper = ModelMapper()

    fun personToDto(person: Person): PersonDTO {
        val personDTO = PersonDTO()
        modelMapper.map(person, personDTO)
        return personDTO
    }

    fun peopleListToDtos(people: List<Person>): List<PersonDTO> {
        return people.map { personToDto(it) }
    }

    fun dtoToPerson(personDto: PersonDTO): Person {
        val person = Person()
        modelMapper.map(personDto, person)
        return person
    }

    fun peopleDtoListToPersonList(peopleDtos: List<PersonDTO>): List<Person> {
        return peopleDtos.map { dtoToPerson(it) }
    }
}