package com.JeongCommunity.Question;

import com.JeongCommunity.domain.Question;
import com.JeongCommunity.dto.AddQuestionRequest;
import com.JeongCommunity.repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    QuestionRepository questionRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        questionRepository.deleteAll();
    }

    @DisplayName("addQuestion: 질문 글 추가 성공")
    @Test
    public void addQuestion() throws Exception {
        // given : 요청 객체 생성
        final String url = "/api/question";
        final String title = "title";
        final String content = "content";
        final AddQuestionRequest userRequest = new AddQuestionRequest(title, content);

        // JSON으로 직렬화 (객체를 String JSON으로 바꾼다)
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        // 설정한 내용을 요청으로 전송한다
        ResultActions result = mockMvc.perform(post(url).
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Question> questions = questionRepository.findAll();

        assertThat(questions.size()).isEqualTo(1);
        assertThat(questions.get(0).getTitle()).isEqualTo(title);
        assertThat(questions.get(0).getContent()).isEqualTo(content);

    }

}