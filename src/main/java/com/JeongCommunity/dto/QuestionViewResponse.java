package com.JeongCommunity.dto;

import com.JeongCommunity.Question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
public class QuestionViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public QuestionViewResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.createdAt = question.getCreatedAt();
    }
}
