package com.example.coursework2;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Map<Integer, Question> questions;

    public JavaQuestionService() {
        questions = new HashMap<>();
    }

    public Map<Integer, List<Question>> getQuestions() {
        Map<Integer, List<Question>> result = new HashMap<>();
        for (Map.Entry<Integer, Question> entry : questions.entrySet()) {
            result.put(entry.getKey(), Collections.singletonList(entry.getValue()));
        }
        return result;
    }

    /*public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return (Question) questions.values().toArray()[random.nextInt(questions.size())];
    }*/

    public Question addQuestion(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return questions.put(questions.size()+1, newQuestion);
    }

    public Map<Integer, List<Question>> removeQuestion(Integer key) {
        if (questions.containsKey(key)) {
            questions.remove(key);
            for (int i = key+1; i <= questions.size(); i++) {
                questions.put(i-1, questions.get(i));
            }
            questions.remove(questions.size());
            return getQuestions();
        }else throw new NonKeyException("There is no question with this key");
    }


}
