package net.lscrp.ucp.quiz;

import net.lscrp.ucp.server.model.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    Question mapTo(QuestionModel question);

    List<Question> mapTo(List<QuestionModel> questions);
}
