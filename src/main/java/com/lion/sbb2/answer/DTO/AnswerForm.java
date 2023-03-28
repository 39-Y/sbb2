package com.lion.sbb2.answer.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerForm {
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
}
