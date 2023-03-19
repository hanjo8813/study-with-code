package com.example.practicekotlin.sealed.kt

sealed class Parent
class A : Parent()
class B : Parent()
object C : Parent()

fun temp(parent : Parent) : String{
    return when(parent){
        is A -> "a"
        is B -> "b"
        is C -> "c"
//        else -> "else"
    }
}