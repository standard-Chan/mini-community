package com.JeongCommunity.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    서비스 계층에서 요청을 받을 객체
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddQuestionRequest {

    private String title;
    private String content;

    // DTO를 엔티티로 만들어주는 메서드 (엔티티로 변환하는 용도로 사용한다.)
    public Question toEntity() {
        return Question.builder()
                .title(title).
                content(content)
                .build();
    }
}
