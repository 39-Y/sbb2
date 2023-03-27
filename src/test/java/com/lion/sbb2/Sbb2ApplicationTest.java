package com.lion.sbb2;

import com.lion.sbb2.answer.entity.Answer;
import com.lion.sbb2.answer.entity.AnswerRepository;
import com.lion.sbb2.question.entity.Question;
import com.lion.sbb2.question.entity.QuestionRepository;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Sbb2ApplicationTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;
    int id_1;
    int id_2;
    @BeforeEach
    void before(){
        id_1 = questionRepository.save(Question.builder()
                .subject("질문1")
                .content("질문 내용1").build()).getId();
        id_2 = questionRepository.save(Question.builder()
                .subject("질문2")
                .content("질문 내용2").build()).getId();
    }
//    @AfterEach
//    void after(){
//        questionRepository.deleteAll();
//    }
    @Test
    @DisplayName("question 데이터 생성 테스트")
    void t1(){
        Integer id = questionRepository.save(Question.builder()
                .subject("질문1")
                .content("질문 내용1").build()).getId();
        Question q = questionRepository.findById(id).orElse(null);
        Assertions.assertThat(q.getContent()).isEqualTo("질문 내용1");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+q.getCreateDate());
    }

    @Test
    @DisplayName("findAll을 통해 Question의 개수 파악")
    void t2(){
        List<Question> questions = questionRepository.findAll();
        Assertions.assertThat(questions.size()).isEqualTo(2);
        System.out.println(">>>>>>>>>>>>>>>>>>>>id1:"+id_1+"///id2:"+id_2);
        for (Question q:questions) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>"+q.getId());
        }
        Assertions.assertThat(questions.get(0).getContent()).isEqualTo("질문 내용1");

    }

    @Test
    @DisplayName("findById를 통해 Question 가져오기")
    void t3(){
        Question question = questionRepository.findById(id_1).orElse(null);
        Assertions.assertThat(question.getContent()).isEqualTo("질문 내용1");
    }
    @Test
    @DisplayName("findBySubject를 통해 Question 가져오기")
    void t4(){
        Assertions.assertThat(questionRepository.findBySubject("질문1").getId()).isEqualTo(id_1);
    }

    @Test
    @DisplayName("findBySubjectLike로 검색하기")
    void t5(){
        Assertions.assertThat(questionRepository.findBySubjectLike("질문%").size()).isEqualTo(2);
    }

    @Test
    @DisplayName("question 수정하기")
    void t6(){
        Question q = questionRepository.findById(id_1).orElse(null);
        q.setSubject("질문1 새로운 제목");
        questionRepository.save(q);
        Assertions.assertThat(questionRepository.findById(id_1).orElse(null).getSubject())
                .isEqualTo("질문1 새로운 제목");
    }

    @Test
    @DisplayName("question 삭제하기")
    void t7(){
        Question q = questionRepository.findById(id_1).orElse(null);
        questionRepository.delete(q);
        Assertions.assertThat(questionRepository.findAll().size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("question에 답변 달기")
    @Transactional
    void t8(){
        Question q = questionRepository.findById(id_1).orElse(null);
        Answer a = Answer.builder()
                .content("답변 1 입니다.")
                .question(q)
                .build();

        if (q.getAnswers() == null) {
            q.setAnswers(new ArrayList<>());
        }
        answerRepository.save(a);
        Assertions.assertThat(q.getAnswers().get(0).getContent())
                .isEqualTo("답변 1 입니다.");
    }
}