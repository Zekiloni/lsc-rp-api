package com.crp.ucp.quiz;

import com.crp.ucp.server.model.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    Question mapTo(QuestionModel question);

    List<Question> mapTo(List<QuestionModel> questions);
}
