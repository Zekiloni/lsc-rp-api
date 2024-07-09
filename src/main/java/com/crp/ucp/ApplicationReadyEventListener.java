package com.crp.ucp;

import com.crp.ucp.quiz.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class ApplicationReadyEventListener {

    private final QuizService quizService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        try {
            quizService.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}