package com.example.demo.controller

import com.example.demo.dto.PersonDTO
import com.example.demo.exceptions.MVRInvalidArgumentException
import com.example.demo.mapper.PersonMapper
import com.example.demo.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/people")
class PeopleController(val peopleService: PersonService,
                       val personMapper: PersonMapper) {
    @GetMapping
    fun getPeople(): List<PersonDTO> {
        return personMapper.peopleListToDtos(peopleService.findAllPeople())
    }

    @PostMapping
    fun createPerson(@RequestBody personDto: PersonDTO): PersonDTO {
        personDto.id?.let {
            throw MVRInvalidArgumentException("Id present for person to create")
        }
        return personMapper.personToDto(peopleService.savePerson(personMapper.dtoToPerson(personDto)))
    }

    @PutMapping
    fun updatePerson(@RequestBody personDto: PersonDTO): PersonDTO {
        personDto.id?.let { return personMapper.personToDto(peopleService.savePerson(personMapper.dtoToPerson(personDto))) }
        throw MVRInvalidArgumentException("No id for person to update")
    }
}