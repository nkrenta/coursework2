package com.example.coursework2;

import com.example.coursework2.Exceptions.NonKeyException;
import com.example.coursework2.Services.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void testGetAllQuestions() {
        // Arrange
        javaQuestionService.addQuestion("What is Java?", "A programming language.");
        javaQuestionService.addQuestion("What is OOP?", "Object-Oriented Programming.");
        // Act
        Map<Integer, List<Question>> questions = javaQuestionService.getAllQuestions();
        // Assert
        assertEquals(2, questions.size());
    }

    @Test
    void testAddQuestion() {
        // Act
        Question question = javaQuestionService.addQuestion("What is Java?", "A programming language.");
        // Assert
        assertNotNull(question);
        assertEquals("What is Java?", question.getQuestion());
    }

    @Test
    void testRemoveQuestion() {
        // Arrange
        javaQuestionService.addQuestion("What is Java?", "A programming language.");
        // Act
        Map<Integer, List<Question>> updatedQuestions = javaQuestionService.removeQuestion(1);
        // Assert
        assertEquals(0, updatedQuestions.size());
    }

    @Test
    void testRemoveNonExistentQuestion() {
        // Assert
        assertThrows(NonKeyException.class, () -> javaQuestionService.removeQuestion(999));
    }
}
