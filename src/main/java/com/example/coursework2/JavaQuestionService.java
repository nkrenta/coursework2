package com.example.coursework2;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService {

    private Map<Integer, Question> questions;
    public JavaQuestionService(){questions = new HashMap<Integer, Question>();}

    private int COUNTERQUESTIONS = 1;

    public Map<String, List<Question>> getQuestions() {
        List<Question> questionsList = new ArrayList<>(questions.values());
        return (Map<String, List<Question>>) questionsList.stream()
                .distinct()
                .map(String::valueOf);
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return (Question) questions.values().toArray()[random.nextInt(questions.size())];
    }

    public Question addQuestion(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        COUNTERQUESTIONS += 1;
        return questions.put(COUNTERQUESTIONS-1, newQuestion);
    }

    public Question removeQuestion(String question, String answer) {
        return questions.remove(question);
    }
}
