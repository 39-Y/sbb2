package com.lion.sbb2.domain;

import com.lion.sbb2.question.entity.Question;
import com.lion.sbb2.question.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class putTestData {
    @Bean
    public CommandLineRunner insert(QuestionService service){
        return args -> {
          service.save(Question.builder().subject("sbb는 무엇을 하는 곳인가요?")
                  .content("항상 궁금했습니다.").build());
          service.save(Question.builder().subject("spring boot에서만 jpa를 쓰나요?")
                  .content("jpa 쓰는게 어렵네요.").build());
        };
    }
}
