package com.JeongCommunity.Question;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter
@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question")
    public List<Question> question() {
        return questionService.getAllQuestion();
    }
}
