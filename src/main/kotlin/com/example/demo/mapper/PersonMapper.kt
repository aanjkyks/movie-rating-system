package com.example.demo.mapper

import com.example.demo.dto.PersonDTO
import com.example.demo.model.Person
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonMapper {
    private var modelMapper = ModelMapper()

    fun personToDto(person: Person): PersonDTO {
        val personDTO = PersonDTO()
        modelMapper.map(person, personDTO)
        person.photo?.let { personDTO.photo = Base64.getEncoder().encodeToString(it) }
        return personDTO
    }

    fun peopleListToDtos(people: List<Person>): List<PersonDTO> {
        return people.map { personToDto(it) }
    }

    fun dtoToPerson(personDto: PersonDTO): Person {
        val person = Person()
        modelMapper.map(personDto, person)
        personDto.photo?.let { person.photo = Base64.getDecoder().decode(it) }
        return person
    }

    fun peopleDtoListToPersonList(peopleDtos: List<PersonDTO>): List<Person> {
        return peopleDtos.map { dtoToPerson(it) }
    }
}