package com.example.coursework2.Services;

import com.example.coursework2.Exceptions.NonKeyException;
import com.example.coursework2.Question;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JavaQuestionService implements QuestionService {

    private final Map<Integer, Question> questions;

    public JavaQuestionService() {
        questions = new HashMap<>();
    }

    public Map<Integer, List<Question>> getAllQuestions() {
        Map<Integer, List<Question>> result = new HashMap<>();
        for (Map.Entry<Integer, Question> entry : questions.entrySet()) {
            result.put(entry.getKey(), Collections.singletonList(entry.getValue()));
        }
        return result;
    }

    public Question addQuestion(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.put(questions.size() + 1, newQuestion);
        return newQuestion;
    }

    public Map<Integer, List<Question>> removeQuestion(Integer key) {
        if (questions.containsKey(key)) {
            questions.remove(key);
            for (int i = key + 1; i <= questions.size(); i++) {
                questions.put(i - 1, questions.get(i));
            }
            questions.remove(questions.size());
            return getAllQuestions();
        } else throw new NonKeyException("There is no question with this key");
    }

}
