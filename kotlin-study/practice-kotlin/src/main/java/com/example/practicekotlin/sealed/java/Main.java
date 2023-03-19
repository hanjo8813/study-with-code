package com.example.practicekotlin.sealed.java;

class Parent {

}

class A extends Parent {

}

class B extends Parent {

}

class C extends Parent {

}


public class Main {

    public static String temp(Parent parent) {
        if(parent instanceof A) return "a";
        if(parent instanceof B) return "b";
        if(parent instanceof C) return "c";
        else return "other";
    }
}
