package com.JeongCommunity.dto;

import com.JeongCommunity.domain.Question;
import lombok.Getter;

@Getter
public class QuestionListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public QuestionListViewResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
    }
}
