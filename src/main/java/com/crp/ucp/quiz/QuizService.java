package com.crp.ucp.quiz;

import com.crp.ucp.server.model.QuestionAnswerSubmit;
import com.crp.ucp.server.model.QuizResult;
import com.crp.ucp.server.model.QuizSubmit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QuizService {

    private final static int MAX_QUIZ_QUESTIONS = 7;
    private final static int MAX_INCORRECT_ANSWERS = 2;

    private final ObjectMapper objectMapper;
    private final QuestionService questionService;

    public void initialize() throws IOException {
        InputStream inputStream = new ClassPathResource("data/questions.json").getInputStream();

        List<QuestionModel> allQuestions = objectMapper.readValue(inputStream, new TypeReference<>() {});

        allQuestions.forEach(questionService::createQuestion);

        log.info("Initialized roleplay quiz, number of questions: {}", allQuestions.size());
    }

    public List<QuestionModel> createQuiz() {
        List<QuestionModel> allQuestions = questionService.getQuestionRegistry();

        Collections.shuffle(allQuestions);
        allQuestions.forEach(this::shuffleAnswers);

        return allQuestions.stream()
                .distinct()
                .limit(MAX_QUIZ_QUESTIONS)
                .collect(Collectors.toList());
    }

    private void shuffleAnswers(QuestionModel question) {
        Collections.shuffle(question.getAnswers());
    }

    public QuizResult processQuiz(QuizSubmit quizSubmit) {
        if (quizSubmit.getAnswers().size() != MAX_QUIZ_QUESTIONS)
            throw new IllegalArgumentException("Potrebno je odgovoriti na sva pitanja");

        List<String> wrongAnswerIds = quizSubmit.getAnswers().stream()
                .filter(this::processQuizAnswer)
                .map(QuestionAnswerSubmit::getQuestionId)
                .toList();

        boolean failed = wrongAnswerIds.size() >= MAX_INCORRECT_ANSWERS;

        return new QuizResult(failed, MAX_INCORRECT_ANSWERS, wrongAnswerIds);
    }

    private boolean processQuizAnswer(QuestionAnswerSubmit questionAnswerSubmit) {
        return !questionService.checkQuestionAnswer(questionAnswerSubmit.getQuestionId(), questionAnswerSubmit.getAnswerId());
    }
}
