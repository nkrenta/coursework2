package com.example.coursework2.Exceptions;

public class EmptyListOfQuestions extends RuntimeException {
    public EmptyListOfQuestions(String message) {
        super(message);
    }
}
