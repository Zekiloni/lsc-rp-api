package net.lscrp.ucp.account.authentication;

import net.lscrp.ucp.server.api.AuthenticationApi;
import net.lscrp.ucp.server.model.Account;
import net.lscrp.ucp.server.model.Authentication;
import net.lscrp.ucp.server.model.AuthenticationStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements net.lscrp.ucp.server.api.AuthenticationApi {

    private final NativeWebRequest nativeWebRequest;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }

    private final AuthenticationService authenticationService;

    @Getter
    private final HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<AuthenticationStatus> authenticate(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.handle(authentication));
    }

    @Override
    public ResponseEntity<Account> validate() {
        if (getRequest().isPresent()) {
            String token = this.getRequest().get().getHeader("Authorization");
            Optional<Account> account = this.authenticationService.validate(token);

            if (account.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(account.get());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
