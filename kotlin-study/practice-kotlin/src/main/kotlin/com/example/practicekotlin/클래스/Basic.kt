package com.example.practicekotlin

// 부생성자
class Temp1 {
    var a: Int

    constructor(a: Int) {
        this.a = a
    }
}

// 주생성자 + init
class Temp2 constructor(a: Int) {
    var a: Int

    init {
        this.a = a
    }
}

// 주생성자 키워드 생략 + init
class Temp3(a: Int) {
    var a: Int

    init {
        this.a = a
    }
}

// 주생성자 - 프로퍼티
class Temp4(var a: Int) {

}

class Temp5 {
    var a: Int = 0
    var b: Int = 2
}

fun main() {
    Temp4().a
}