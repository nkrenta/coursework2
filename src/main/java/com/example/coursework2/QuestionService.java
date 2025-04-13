package com.example.coursework2;

import com.example.coursework2.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    Question addQuestion(String question, String answer);

    Question removeQuestion(String question, String answer);

    Map<String, List<Question>> getQuestions();
}
