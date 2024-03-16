package com.crp.ucp.account.authentication;

import com.crp.ucp.server.model.Account;
import com.crp.ucp.server.model.Authentication;
import com.crp.ucp.server.model.AuthenticationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationApi implements com.crp.ucp.server.api.AuthenticationApi {

    private final AuthenticationService authenticationService;

    private final NativeWebRequest nativeWebRequest;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(nativeWebRequest);
    }

    @Override
    public ResponseEntity<AuthenticationStatus> authenticate(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.handle(authentication));
    }

    @Override
    public ResponseEntity<Account> validate() {
        if (this.getRequest().isPresent()) {
            String token = this.getRequest().get().getHeader("Authorization");
            Optional<Account> account = this.authenticationService.validate(token);

            if (account.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(account.get());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
