package com.crp.ucp.account.authentication;


import com.crp.ucp.server.model.Authentication;
import com.crp.ucp.server.model.AuthenticationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationApi implements com.crp.ucp.server.api.AuthenticationApi {

    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<AuthenticationStatus> authenticate(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.handle(authentication));
    }
}
