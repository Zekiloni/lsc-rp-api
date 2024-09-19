package net.lscrp.ucp.account.password.reset;

import lombok.RequiredArgsConstructor;
import net.lscrp.ucp.account.AccountEntity;
import net.lscrp.ucp.account.AccountService;
import net.lscrp.ucp.mail.MailService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static net.lscrp.ucp.account.AccountUtil.throwAccountNotFoundException;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    public static final int PASSWORD_TOKEN_EXPIRING_MIN = 30;

    private final AccountService accountService;
    private final MailService mailService;
    private final PasswordResetRepository passwordResetRepository;

    Optional<PasswordResetEntity> getByToken(String token) {
        return passwordResetRepository.findByToken(token);
    }

    public PasswordResetEntity create(String username) {
        AccountEntity account = accountService.getAccountByUsername(username)
                .orElseThrow(() -> throwAccountNotFoundException(username));

        PasswordResetEntity passwordReset = PasswordResetEntity.builder()
                .account(account)
                .token(UUID.randomUUID().toString())
                .createdAt(OffsetDateTime.now())
                .expiringAt(OffsetDateTime.now().plusMinutes(PASSWORD_TOKEN_EXPIRING_MIN))
                .build();

        mailService.sendPasswordResetEmail(account.getEmail(), passwordReset.getToken());

        return passwordResetRepository.save(passwordReset);
    }

    public void handle(String resetToken, String newPassword) {
        PasswordResetEntity passwordReset = getByToken(resetToken)
                .orElseThrow(() -> new ResetPasswordTokenException("Nevažeći token za resetiranje lozinke"));

        if (passwordReset.isExpired()) {
            throw new ResetPasswordTokenException("Token za resetovanje lozinke je istekao");
        }

        String salt = BCrypt.gensalt();
        passwordReset.getAccount().setPassword(BCrypt.hashpw(newPassword, salt));
    }
}
