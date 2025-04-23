package com.example.coursework2.Exceptions;

public class NotEnoughQuestions extends RuntimeException {
    public NotEnoughQuestions(String message) {
        super(message);
    }
}
