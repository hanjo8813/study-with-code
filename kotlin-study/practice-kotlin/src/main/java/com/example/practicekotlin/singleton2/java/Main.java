package com.example.practicekotlin.singleton2.java;

class Dummy {

    public static final int CONST = 1;
    private Dummy() {
    }

    public static Dummy getInstance() {
        return new Dummy();
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println(Dummy.CONST);
        System.out.println(Dummy.getInstance());
    }
}
