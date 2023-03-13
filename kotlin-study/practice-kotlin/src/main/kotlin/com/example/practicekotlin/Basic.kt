package com.example.practicekotlin

enum class Enums {
    A {
        override fun temp(): Int = 1
    },
    B {
        override fun temp(): Int = 2
    };

    abstract fun temp(): Int
}

fun main() {
    val mapOf = mapOf<String, Int>()

}


data class Temp{
    var a: Int
}