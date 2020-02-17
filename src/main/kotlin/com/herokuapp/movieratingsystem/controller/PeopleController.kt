package com.herokuapp.movieratingsystem.controller


import com.herokuapp.movieratingsystem.dto.PersonDTO
import com.herokuapp.movieratingsystem.exceptions.MVRInvalidArgumentException
import com.herokuapp.movieratingsystem.mapper.PersonMapper
import com.herokuapp.movieratingsystem.service.PersonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/people")
class PeopleController(private val peopleService: PersonService,
                       private val personMapper: PersonMapper) {
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

    @GetMapping
    fun findPeople(@RequestParam(required = false) name: String?,
                   @PageableDefault pageable: Pageable): Page<PersonDTO> {
        return personMapper.peopleListToDtos(peopleService.findPeopleByName(name, pageable))
    }

    @GetMapping("/{id}")
    fun findPerson(@PathVariable id: Long): PersonDTO {
        return personMapper.personToDto(peopleService.findPersonById(id))
    }
}