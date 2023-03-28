package com.lion.sbb2.question.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(max=200)
    private String subject;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
}
