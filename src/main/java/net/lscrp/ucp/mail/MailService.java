package net.lscrp.ucp.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailService {

    @Value("${no-reply.mail}")
    private String mailFrom;

    @Value("${app.password-reset-url}")
    private String appResetPasswordUrl;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(mailFrom);

        mailSender.send(message);
    }

    private void sendHtmlMail(String to, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom(mailFrom);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void sendPasswordResetEmail(String to, String resetToken) {
        String resetUrl = generateResetPasswordUrl(resetToken);

        Context context = new Context();
        context.setVariable("resetUrl", resetUrl);

        String body = templateEngine.process("password-reset-mail", context);

        sendHtmlMail(to, "Zahtev za resetiranje lozinke", body);
    }

    private String generateResetPasswordUrl(String resetToken) {
        return String.format("%s/%s", appResetPasswordUrl, resetToken);
    }
}
