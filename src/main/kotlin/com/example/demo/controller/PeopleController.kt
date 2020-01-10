package com.example.demo.controller

import com.example.demo.dto.PersonDTO
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
        return personMapper.personToDto(peopleService.savePerson(personMapper.dtoToPerson(personDto)))
    }
}