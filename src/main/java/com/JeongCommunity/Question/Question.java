package com.JeongCommunity.Question;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 생성자 생성
@Setter
@Getter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

//    private LocalDateTime createDate;

    // builder 패턴을 사용
    //=> ex) Question.builder().title("name").content("내용").createDate("time").build();
    @Builder
    public Question(String title, String content) {
        this.title = title;
        this.content = content;
        //this.createDate = localDateTime;
    }

}
