package com.example.demo.model

import java.io.Serializable
import javax.persistence.*

@Entity
class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String = "",
        @Column(columnDefinition = "BLOB")
        var photo: ByteArray? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false
        if (name != other.name) return false
        if (photo != null) {
            if (other.photo == null) return false
            if (!photo!!.contentEquals(other.photo!!)) return false
        } else if (other.photo != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + (photo?.contentHashCode() ?: 0)
        return result
    }
}