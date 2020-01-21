package com.example.demo.dto

class MoviePersonInfo(
        var id: Long? = null,
        var name: String = "",
        var movies: List<MoviePersonDto> = emptyList()
)