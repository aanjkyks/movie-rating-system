package com.example.demo.repository

import com.example.demo.model.MovieActor
import org.springframework.data.jpa.repository.JpaRepository

interface MovieActorRepository : JpaRepository<MovieActor, Long>{
}