package com.example.practicekotlin.singleton2.kt

class Dummy {

    private constructor()

    companion object {
        const val CONST = 1
        fun getInstance() = Dummy()
    }
}

fun main() {
    println(Dummy.CONST)
    println(Dummy.getInstance())
}