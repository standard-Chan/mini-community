package com.JeongCommunity.dto;

import com.JeongCommunity.Question.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public QuestionViewResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.createdAt = question.getCreatedAt();
    }
}
