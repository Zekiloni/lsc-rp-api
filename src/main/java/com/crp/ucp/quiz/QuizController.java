package com.crp.ucp.quiz;

import com.crp.ucp.server.api.QuizApi;
import com.crp.ucp.server.model.Question;
import com.crp.ucp.server.model.QuizResult;
import com.crp.ucp.server.model.QuizSubmit;
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
