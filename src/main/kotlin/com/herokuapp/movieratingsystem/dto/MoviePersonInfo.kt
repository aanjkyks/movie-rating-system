package com.herokuapp.movieratingsystem.dto

class MoviePersonInfo(
        var id: Long? = null,
        var name: String = "",
        var photo: String? = null,
        var movies: List<MoviePersonDto> = emptyList()
)