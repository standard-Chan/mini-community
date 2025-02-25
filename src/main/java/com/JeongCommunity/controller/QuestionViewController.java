package com.JeongCommunity.controller;

import com.JeongCommunity.Question.Question;
import com.JeongCommunity.Question.QuestionService;
import com.JeongCommunity.dto.QuestionListViewResponse;
import com.JeongCommunity.dto.QuestionViewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/questions/{id}")
    public String getQuestion(@PathVariable Long id, Model model) {
        Question question = questionService.findById(id);
        model.addAttribute("question", new QuestionViewResponse(question));

        return "question";
    }

    // 글 작성 및 수정
    @GetMapping("/new-question")
    public String newQuestion(@RequestParam(required = false) Long id, Model model) {
        // 글 새로 작성
        if (id == null) {
            model.addAttribute("question", new QuestionViewResponse());
        } else {
            Question question = questionService.findById(id);
            model.addAttribute("question", new QuestionViewResponse(question));
        }

        return "newQuestion";
    }
}
