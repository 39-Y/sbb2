package com.lion.sbb2.answer.controller;

import com.lion.sbb2.answer.DTO.AnswerForm;
import com.lion.sbb2.answer.entity.Answer;
import com.lion.sbb2.answer.service.AnswerService;
import com.lion.sbb2.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    final AnswerService service;
    final QuestionService questionService;
    @PostMapping("/create/{id}")
    public String create(Model model, @PathVariable Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            model.addAttribute("question", questionService.getQuestion(id));
            return "question/question_detail";
        }
        service.create(answerForm.getContent(), id);
        return "redirect:/question/detail/"+id;

    }
}
