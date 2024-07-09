package com.crp.ucp.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class QuestionAnswerModel {
    private String id;
    private String answer;
    private boolean isCorrect;
}
