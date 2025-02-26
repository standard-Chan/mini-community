package com.JeongCommunity.dto;

import com.JeongCommunity.domain.Question;
import lombok.Getter;

@Getter
public class QuestionResponse {
    private final String title;
    private final String content;

    public QuestionResponse(Question question) {
        this.title = question.getTitle();
        this.content = question.getContent();
    }

}
