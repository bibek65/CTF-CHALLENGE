package com.example;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hello, World!";
        }
        return "Hello, " + name + "!";
    }
}
