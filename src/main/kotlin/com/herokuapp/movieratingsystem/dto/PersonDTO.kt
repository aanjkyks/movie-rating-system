package com.herokuapp.movieratingsystem.dto

class PersonDTO(
        var id: Long? = null,
        var name: String = "",
        var photo: String? = null,
        var role: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PersonDTO

        if (id != other.id) return false
        if (name != other.name) return false
        if (photo != other.photo) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + (photo?.hashCode() ?: 0)
        result = 31 * result + role.hashCode()
        return result
    }

    override fun toString(): String {
        return "PersonDTO(id=$id, name='$name', photo=$photo, role='$role')"
    }
}