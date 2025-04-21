package com.example.coursework2.Services;

import com.example.coursework2.Question;

import java.util.Map;

public interface ExaminerService {

    Map<Integer, Question> getQuestions(Integer amount);
}
