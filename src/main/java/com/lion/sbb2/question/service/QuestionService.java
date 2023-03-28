package com.lion.sbb2.question.service;

import com.lion.sbb2.domain.DataNotFoundException;
import com.lion.sbb2.question.entity.Question;
import com.lion.sbb2.question.entity.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    final QuestionRepository repository;
    public Page<Question> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        return repository.findAll(PageRequest.of(page, 10, Sort.by(sorts)));
    }
    public Question save(String subject, String content){
        return repository.save(Question.builder()
                .subject(subject)
                .content(content).build());
    }

    public Question getQuestion(Integer id) {
        Question q = repository.findById(id).orElse(null);
        if(q==null)
            throw new DataNotFoundException("question not found");
        return q;

    }
}
