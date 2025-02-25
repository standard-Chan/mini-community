package com.JeongCommunity.Question;

import com.JeongCommunity.dto.AddQuestionRequest;
import com.JeongCommunity.dto.UpdateQuestionRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("/api/questions")
    public ResponseEntity<Question> addQuestion(@RequestBody AddQuestionRequest request) {
        Question savedQuestion = questionService.save(request);

        // client에게 http를 다시 보내준다.
        // status = CREATED (201)
        // body에는 저장된 question
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

    @PutMapping("/api/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable long id,
                                                           @RequestBody UpdateQuestionRequest request) {
        Question updateQuestion = questionService.update(id, request);
        return ResponseEntity.ok().body(updateQuestion);
    }
}
