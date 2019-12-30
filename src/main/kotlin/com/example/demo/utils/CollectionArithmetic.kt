package com.example.demo.utils

class CollectionArithmetic<T> {

    fun difference(a: Collection<T>, b: Collection<T>): Collection<T> {
        return a.filter { !b.contains(it) }
    }

    fun union(a: Collection<T>, b: Collection<T>): Collection<T> {
        a as MutableList
        a.addAll(b)
        return a
    }

    fun intersection(a: Collection<T>, b: Collection<T>): Collection<T> {
        a as MutableList
        a.retainAll(b)
        return a
    }

    fun symDifference(a: Collection<T>, b: Collection<T>): Collection<T> {
        val result = difference(a, b) as MutableList
        result.addAll(difference(b, a))
        return result
    }
}