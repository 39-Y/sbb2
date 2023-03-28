package com.lion.sbb2.answer.service;

import com.lion.sbb2.answer.entity.Answer;
import com.lion.sbb2.answer.entity.AnswerRepository;
import com.lion.sbb2.question.entity.Question;
import com.lion.sbb2.question.entity.QuestionRepository;
import com.lion.sbb2.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    final AnswerRepository repository;
    final QuestionRepository questionRepository;
    public Answer create(String content, Integer id){
        Question q = questionRepository.findById(id).orElse(null);
        return repository.save(Answer.builder()
                        .content(content)
                        .question(q)
                        .build());
    }
}
