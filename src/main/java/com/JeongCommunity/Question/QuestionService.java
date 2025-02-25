package com.JeongCommunity.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor // final, @NotNull인 필드 생성자 자동으로 생성
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question save(AddQuestionRequest request) {
        System.out.println("saved data's title");
        System.out.println(request.getTitle());
        return questionRepository.save(request.toEntity());
    }

    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        questionRepository.deleteById(id);
    }
}
