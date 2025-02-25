package com.JeongCommunity.Question;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 생성자 생성
@Setter
@Getter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // builder 패턴을 사용
    //=> ex) Question.builder().title("name").content("내용").createDate("time").build();
    @Builder
    public Question(String title, String content) {
        this.title = title;
        this.content = content;
        //this.createDate = localDateTime;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
