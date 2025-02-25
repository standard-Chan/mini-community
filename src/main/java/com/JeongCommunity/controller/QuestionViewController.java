package com.JeongCommunity.controller;

import com.JeongCommunity.Question.Question;
import com.JeongCommunity.Question.QuestionService;
import com.JeongCommunity.dto.QuestionListViewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuestionViewController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public String getQuestions(Model model) {
        // Entity를 직접 담아서 넣을 수도 있지만, DTO로 변환하여 전달하자.
        List<QuestionListViewResponse> questions = questionService.findAll().stream()
                .map(QuestionListViewResponse::new)
                .toList();
        model.addAttribute("questions", questions);

        return "questionList";
    }

    @GetMapping("/questions2")
    public ResponseEntity<List<QuestionListViewResponse>> getQuestions() {
        // Entity를 직접 담아서 넣을 수도 있지만, DTO로 변환하여 전달하자.
        List<QuestionListViewResponse> questions = questionService.findAll().stream()
                .map(QuestionListViewResponse::new)
                .toList();

        return ResponseEntity.ok().body(questions);
    }
}
