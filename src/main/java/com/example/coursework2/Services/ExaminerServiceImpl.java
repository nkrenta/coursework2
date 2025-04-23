package com.example.coursework2.Services;

import com.example.coursework2.Exceptions.BadRequest;
import com.example.coursework2.Exceptions.EmptyListOfQuestions;
import com.example.coursework2.Exceptions.NotEnoughQuestions;
import com.example.coursework2.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService JavaQuestionService;
    private final Set<Integer> usedRandomIndexes = new HashSet<>();

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.JavaQuestionService = javaQuestionService;
    }

    public int getRandomQuestion() {
        Map<Integer, List<Question>> allQuestions = JavaQuestionService.getAllQuestions();
        if (allQuestions.isEmpty()) {
            throw new EmptyListOfQuestions("List of questions is empty");
        }
        Random random = new Random();
        int questionCount = allQuestions.size();
        if (usedRandomIndexes.size() >= questionCount) {
            throw new NotEnoughQuestions("All questions have been used.");
        }
        int randomIndex;
        do {
            randomIndex = random.nextInt(questionCount);
        } while (usedRandomIndexes.contains(randomIndex));

        usedRandomIndexes.add(randomIndex);
        return randomIndex;
    }

    public Map<Integer, String> getQuestions(Integer amount) {
        Map<Integer, List<Question>> allQuestions = JavaQuestionService.getAllQuestions();

        List<String> questionsWithoutAnswers = allQuestions.values().stream()
                .flatMap(List::stream)
                .map(Question::getQuestion)
                .toList();

        if (amount > questionsWithoutAnswers.size()) {
            throw new BadRequest("Requested amount exceeds available questions without answers.");
        }

        Map<Integer, String> uniqueQuestions = new HashMap<>();
        while (uniqueQuestions.size() < amount) {
            int randomIndex = getRandomQuestion();

            if (randomIndex >= 0 && randomIndex < questionsWithoutAnswers.size()) {
                uniqueQuestions.put(uniqueQuestions.size() + 1, questionsWithoutAnswers.get(randomIndex));
            }
        }
        usedRandomIndexes.clear();
        return uniqueQuestions;
    }
}
