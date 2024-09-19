package net.lscrp.ucp.account.password.reset;

import lombok.RequiredArgsConstructor;
import net.lscrp.ucp.server.api.PasswordResetApi;
import net.lscrp.ucp.server.model.MessageResponse;
import net.lscrp.ucp.server.model.ResetPassword;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasswordResetController implements PasswordResetApi {

    private final PasswordResetService passwordResetService;

    @Override
    public ResponseEntity<MessageResponse> createPasswordReset(String username) {
        String email = passwordResetService.create(username).getAccount().getEmail();
        MessageResponse body = createMessageResponse(String.format("Mail za resetiranje lozinke " +
                "je poslan na adresu %s", email));
        return ResponseEntity.ok(body);
    }

    private static MessageResponse createMessageResponse(String message) {
        return new MessageResponse(message);
    }

    @Override
    public ResponseEntity<MessageResponse> handlePasswordReset(String token, ResetPassword resetPassword) {
        passwordResetService.handle(token, resetPassword.getNewPassword());
        MessageResponse body = createMessageResponse("Uspesno resetovana lozinka");
        return ResponseEntity.ok(body);
    }
}
