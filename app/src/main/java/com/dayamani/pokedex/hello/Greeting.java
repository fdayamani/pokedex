package com.dayamani.pokedex.hello;

public record Greeting(String message) {

    @Override
    public String toString() {
        return "Greeting{" +
                "message='" + message + '\'' +
                '}';
    }
}
