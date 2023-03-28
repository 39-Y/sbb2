package com.lion.sbb2.question.controller;

import com.lion.sbb2.answer.DTO.AnswerForm;
import com.lion.sbb2.domain.DataNotFoundException;
import com.lion.sbb2.question.DTO.QuestionForm;
import com.lion.sbb2.question.entity.Question;
import com.lion.sbb2.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService service;
    @GetMapping("/list")
    public String showList(Model model, @RequestParam(defaultValue = "0") int page){
        model.addAttribute("paging", service.getList(page));
        return "question/question_list";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Integer id, Model model, AnswerForm answerForm){

        Question q = service.getQuestion(id);
        model.addAttribute("question", q);

        return "question/question_detail";
    }
    @GetMapping("/create")
    public String showCreate(QuestionForm questionForm){
        return "question/question_create";
    }

    @PostMapping("/create")
    public String create(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question/question_create";
        }
        int id =service.save(questionForm.getSubject(), questionForm.getContent()).getId();
        return "redirect:/question/detail/"+id;
    }
}
