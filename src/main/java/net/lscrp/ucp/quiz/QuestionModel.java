package net.lscrp.ucp.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class QuestionModel {
    private String id;
    private String question;
    private List<QuestionAnswerModel> answers;
}
