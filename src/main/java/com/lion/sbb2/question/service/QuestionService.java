package com.lion.sbb2.question.service;

import com.lion.sbb2.domain.DataNotFoundException;
import com.lion.sbb2.question.entity.Question;
import com.lion.sbb2.question.entity.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    final QuestionRepository repository;
    public List<Question> getList(){
        return repository.findAll();
    }
    public Question save(Question q){
        return repository.save(q);
    }

    public Question getQuestion(Integer id) {
        Question q = repository.findById(id).orElse(null);
        if(q==null)
            throw new DataNotFoundException("question not found");
        return q;

    }
}
