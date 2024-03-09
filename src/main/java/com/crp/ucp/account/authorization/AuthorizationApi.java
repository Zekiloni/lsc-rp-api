package com.crp.ucp.account.authorization;


import com.crp.ucp.server.model.Authorize;
import com.crp.ucp.server.model.AuthorizeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationApi implements com.crp.ucp.server.api.AuthorizationApi {

    private final AuthorizationService authorizationService;

    @Override
    public ResponseEntity<AuthorizeStatus> authorize(Authorize authorize) {
        return ResponseEntity.ok(authorizationService.handle(authorize));
    }
}
