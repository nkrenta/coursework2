package com.example.coursework2.Controllers;

import com.example.coursework2.Question;
import com.example.coursework2.Services.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/exam")
public class JavaQuestionController {

    private final QuestionService QuestionService;

    public JavaQuestionController(QuestionService questionService) {
        this.QuestionService = questionService;
    }

    @GetMapping
    public String helloExaminee() {
        return """
                Hi, examinee!
                You'll have an exam today!
                Good luck!
                If you want to get questions, you can go to path "/exam/get/{amount}"
                "amount" means a number of questions.
                The path "/exam/java/(add/remove/find)" lets you adding, removing or searching questions""";
    }

    @GetMapping(path = "/java")
    public Map<Integer, List<Question>> getAllQuestions() {
        return QuestionService.getAllQuestions();
    }

    @GetMapping(path = "/java/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return QuestionService.addQuestion(question, answer);
    }

    @GetMapping(path = "/java/remove/{key}")
    public Map<Integer, List<Question>> removeQuestion(@PathVariable(required = false) Integer key) {
        QuestionService.getAllQuestions();
        return QuestionService.removeQuestion(key);
    }

}
