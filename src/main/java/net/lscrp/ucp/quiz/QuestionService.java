package net.lscrp.ucp.quiz;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class QuestionService {

    private static final List<QuestionModel> QUESTION_REGISTRY = new LinkedList<>();

    public List<QuestionModel> getQuestionRegistry() {
        return QUESTION_REGISTRY;
    }

    public void createQuestion(QuestionModel question) {
        question.setId(UUID.randomUUID().toString());

        question.getAnswers()
                        .forEach(QuestionService::createQuestionAnswer);

        QUESTION_REGISTRY.add(question);
    }

    private static void createQuestionAnswer(QuestionAnswerModel questionAnswer) {
        questionAnswer.setId(UUID.randomUUID().toString());
    }

    public boolean checkQuestionAnswer(String questionId, String answerId) {
        QuestionModel question = QUESTION_REGISTRY.stream()
                .filter(getQuestionById(questionId))
                .findAny()
                .orElseThrow(throwQuestionNotFoundException(questionId));

        return isCorrectAnswer(question, answerId);
    }

    private Boolean isCorrectAnswer(QuestionModel question, String answerId) {
        return question.getAnswers()
                .stream()
                .filter(answer -> answer.getId().equals(answerId))
                .map(QuestionAnswerModel::isCorrect)
                .findAny()
                .orElseThrow(throwQuestionAnswerNotFoundException(question.getId(), answerId));
    }

    private Supplier<NoSuchElementException> throwQuestionAnswerNotFoundException(String questionId, String answerId) {
        return () -> new NoSuchElementException(
                format("Answer with ID {0} not found for question with ID {1}", questionId, answerId)
        );
    }

    private Predicate<QuestionModel> getQuestionById(String questionId) {
        return question -> question.getId().equals(questionId);
    }

    private Supplier<NoSuchElementException> throwQuestionNotFoundException(String questionId) {
        return () -> new NoSuchElementException(
                format("Question with ID {0} not found", questionId)
        );
    }
}
