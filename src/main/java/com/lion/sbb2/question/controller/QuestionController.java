package com.lion.sbb2.question.controller;

import com.lion.sbb2.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService service;
    @GetMapping("/list")
    public String showList(Model model){
        model.addAttribute("questionList", service.getList());
        return "question/question_list";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Integer id, Model model){
        model.addAttribute("question", service.getQuestion(id));
        return "question/question_list";
    }
}
