package com.example.practicekotlin;

public class Dummy {

    interface Inter1 {

        void temp();
    }

    interface Inter2 {

        default void temp() {
            System.out.println("Inter2");
        }
    }

    static class Child implements Inter1, Inter2 {

        Child() {

        }

        @Override
        public void temp() {
            System.out.println("Child");
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.temp();
    }
}
