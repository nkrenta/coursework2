package com.example.coursework2;

import com.example.coursework2.Exceptions.BAD_REQUEST;
import com.example.coursework2.Exceptions.EmptyListOfQuestions;
import com.example.coursework2.Services.ExaminerServiceImpl;
import com.example.coursework2.Services.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExaminerServiceImplTest {
    private ExaminerServiceImpl examinerService;
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = Mockito.mock(JavaQuestionService.class);
        examinerService = new ExaminerServiceImpl(javaQuestionService);
    }

    @Test
    void testGetRandomQuestionThrowsExceptionWhenNoQuestions() {
        // Arrange
        when(javaQuestionService.getAllQuestions()).thenReturn(Collections.emptyMap());

        // Assert
        assertThrows(EmptyListOfQuestions.class, () -> examinerService.getRandomQuestion());
    }

    @Test
    void testGetRandomQuestionReturnsValidIndexWhenQuestionsAvailable() {
        // Arrange
        Question question1 = new Question("What is Java?", "A programming language.");
        Question question2 = new Question("What is OOP?", "Object-Oriented Programming.");
        Question question3 = new Question("What's the difference between Java and OOP'?", "Java is a programming language, OOP is a concept.");
        Map<Integer, List<Question>> questionsMap = new HashMap<>();
        questionsMap.put(1, Collections.singletonList(question1));
        questionsMap.put(2, Collections.singletonList(question2));
        questionsMap.put(3, Collections.singletonList(question3));
        when(javaQuestionService.getAllQuestions()).thenReturn(questionsMap);

        // Act
        int randomIndex = examinerService.getRandomQuestion();

        // Assert
        assertTrue(randomIndex >= 1 && randomIndex <= 3);
    }

    @Test
    void testGetQuestionsThrowsExceptionWhenRequestedAmountExceedsAvailable() {
        // Arrange
        Question question = new Question("What is Java?", "A programming language.");
        Map<Integer, List<Question>> questionsMap = new HashMap<>();
        questionsMap.put(1, Collections.singletonList(question));
        when(javaQuestionService.getAllQuestions()).thenReturn(questionsMap);

        // Assert
        assertThrows(BAD_REQUEST.class, () -> examinerService.getQuestions(2));
    }

    @Test
    void testGetQuestionsReturnsCorrectNumberOfUniqueQuestions() {
        // Arrange
        Question question1 = new Question("What is Java?", "A programming language.");
        Question question2 = new Question("What is OOP?", "Object-Oriented Programming.");
        Map<Integer, List<Question>> questionsMap = new HashMap<>();
        questionsMap.put(1, Collections.singletonList(question1));
        questionsMap.put(2, Collections.singletonList(question2));
        when(javaQuestionService.getAllQuestions()).thenReturn(questionsMap);

        // Act
        Map<Integer, Question> questions = examinerService.getQuestions(1);

        // Assert
        assertEquals(1, questions.size());
    }
}
