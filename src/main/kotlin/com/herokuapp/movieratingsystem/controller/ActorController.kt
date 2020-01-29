package com.herokuapp.movieratingsystem.controller


import com.herokuapp.movieratingsystem.dto.MoviePersonInfo
import com.herokuapp.movieratingsystem.mapper.ActorInfoMapper
import com.herokuapp.movieratingsystem.repository.MovieActorRepository
import com.herokuapp.movieratingsystem.service.MovieService
import com.herokuapp.movieratingsystem.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/person")
class ActorController(private val movieService: MovieService,
                      private val personService: PersonService,
                      private val actorInfoMapper: ActorInfoMapper,
                      private val movieActorRepository: MovieActorRepository) {
    @GetMapping("/{id}")
    fun getActorInfo(@PathVariable id: Long): MoviePersonInfo {
        val person = personService.findPersonById(id)
        return actorInfoMapper.actorListToInfoList(person, movieActorRepository.findByActor(person), movieService.findByDirector(person))
    }
}