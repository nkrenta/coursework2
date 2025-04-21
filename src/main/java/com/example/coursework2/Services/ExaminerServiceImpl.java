package com.example.coursework2.Services;

import com.example.coursework2.Exceptions.BAD_REQUEST;
import com.example.coursework2.Exceptions.EmptyListOfQuestions;
import com.example.coursework2.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    public JavaQuestionService JavaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.JavaQuestionService = javaQuestionService;
    }

    public int getRandomQuestion() {
        Map<Integer, List<Question>> allQuestions = JavaQuestionService.getAllQuestions();
        if (allQuestions.isEmpty()) {
            throw new EmptyListOfQuestions("List of questions is empty");
        }
        Random random = new Random();
        return random.nextInt(allQuestions.size() + 1);
    }

    public Map<Integer, Question> getQuestions(Integer amount) {
        Map<Integer, List<Question>> allQuestions = JavaQuestionService.getAllQuestions();

        if (amount > allQuestions.size()) {
            throw new BAD_REQUEST("Requested amount exceeds available questions.");
        }

        Map<Integer, Question> uniqueQuestions = new HashMap<>();
        while (uniqueQuestions.size() < amount) {
            int randomIndex = getRandomQuestion();
            List<Question> questionsList = allQuestions.values().stream().findFirst().orElse(Collections.emptyList());

            if (randomIndex >= 0 && randomIndex < questionsList.size()) {
                uniqueQuestions.put(randomIndex, questionsList.get(randomIndex));
            }
        }
        System.out.println("All Questions: " + allQuestions);
        System.out.println("Unique Questions Size: " + uniqueQuestions.size());
        return uniqueQuestions;
    }
}
