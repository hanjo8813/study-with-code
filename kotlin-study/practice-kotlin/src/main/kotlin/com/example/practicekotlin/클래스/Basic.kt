package com.example.practicekotlin

// 생성자가 없는 필드값은 초기화 해줘야 한다.
class Temp {
    var a: Int = 0
    val b: String = ""

    // 이런식으로 필드값에 커스텀 getter/setter 적용가능
    var c: Int = 0
        get() = 1
        set(value) {
            if(value == 1){
                field = value // backing field라고 함. 필드용 this라 생각하믄 됨
            }
        }
}

fun main() {
    val temp = Temp()

    temp.c
}