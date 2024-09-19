package net.lscrp.ucp.quiz;

import net.lscrp.ucp.server.api.QuizApi;
import net.lscrp.ucp.server.model.Question;
import net.lscrp.ucp.server.model.QuizResult;
import net.lscrp.ucp.server.model.QuizSubmit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuizController implements QuizApi {

    private final QuizService quizService;
    private final QuestionMapper questionMapper;

    @Override
    public ResponseEntity<List<Question>> retrieveQuiz() {
        return ResponseEntity.ok(questionMapper.mapTo(quizService.createQuiz()));
    }

    @Override
    public ResponseEntity<QuizResult> submitQuiz(QuizSubmit quizSubmit) {
        return ResponseEntity.ok(quizService.processQuiz(quizSubmit));
    }
}
