package com.example.coursework2.Controllers;

import com.example.coursework2.Services.ExaminerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @RequestMapping(path = "/hello")
    public String hello() {
        return """
                Hello, examiner!
                You can get questions by path '/exam/get/{amount}'
                """;
    }

    @RequestMapping(path = "get/{amount}")
    public Map<Integer, String> getQuestions(@PathVariable(required = false) String amount) {
        return examinerService.getQuestions(Integer.valueOf(amount));
    }
}
