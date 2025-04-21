package com.example.coursework2.Controllers;

import com.example.coursework2.Question;
import com.example.coursework2.Services.ExaminerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {

    public final ExaminerService ExaminerService;

    public ExamController(ExaminerService ExaminerService) {
        this.ExaminerService = ExaminerService;
    }

    @RequestMapping(path = "get/{amount}")
    public Map<Integer, Question> getQuestions(@PathVariable(required = false) Integer amount) {
        return ExaminerService.getQuestions(amount);
    }
}
