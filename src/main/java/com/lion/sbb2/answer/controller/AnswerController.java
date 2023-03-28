package com.lion.sbb2.answer.controller;

import com.lion.sbb2.answer.entity.Answer;
import com.lion.sbb2.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    final AnswerService service;
    @PostMapping("/create/{id}")
    public String create(String content, @PathVariable Integer id){
        service.create(content, id);
        return "redirect:/question/detail/"+id;

    }
}
