package com.JeongCommunity.Question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("/api/question")
    public ResponseEntity<Question> addQuestion(@RequestBody AddQuestionRequest request) {
        Question savedQuestion = questionService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedQuestion);
    }

    @GetMapping("/api/questions")
    public ResponseEntity<List<QuestionResponse>> findAllQuestions() {
        List<QuestionResponse> questions = questionService.findAll()
                .stream()
                .map(QuestionResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(questions);
    }

    @GetMapping("/api/questions/{id}")
    public ResponseEntity<QuestionResponse> findQuestion(@PathVariable long id) {
        Question question = questionService.findById(id);
        return ResponseEntity.ok()
                .body(new QuestionResponse(question));
    }

    @DeleteMapping("/api/questions/{id}")
    public ResponseEntity<QuestionResponse> deleteQuestion(@PathVariable long id) {
        questionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
