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

    public List<Question> getAllQuestion() {
        List<Question> questions = questionRepository.findAll();
        return questions;
    }

    public Question save(AddQuestionRequest request) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
        System.out.println(request);
        return questionRepository.save(request.toEntity());
    }

    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
