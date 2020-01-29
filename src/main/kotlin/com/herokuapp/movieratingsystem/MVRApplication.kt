package com.herokuapp.movieratingsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MVRApplication

fun main(args: Array<String>) {
	runApplication<MVRApplication>(*args)
}
