package com.example.demo.controller

import com.example.demo.dto.MoviePersonInfo
import com.example.demo.mapper.ActorInfoMapper
import com.example.demo.service.MovieService
import com.example.demo.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/person")
class ActorController(private val movieService: MovieService,
                      private val personService: PersonService,
                      private val actorInfoMapper: ActorInfoMapper) {
    @GetMapping
    fun getActorInfo(@RequestParam id: Long): MoviePersonInfo {
        return actorInfoMapper.actorListToInfoList(personService.findPersonById(id), movieService.findAllMovieActors(), movieService.findAllMovies())
    }
}