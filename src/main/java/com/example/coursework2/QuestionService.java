package com.example.coursework2;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    Question addQuestion(String question, String answer);

    Map<Integer, List<Question>> removeQuestion(Integer key);

    Map<Integer, List<Question>> getQuestions();
}
