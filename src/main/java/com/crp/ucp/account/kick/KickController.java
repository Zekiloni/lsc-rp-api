package com.crp.ucp.account.kick;

import com.crp.ucp.server.model.Kick;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KickController implements com.crp.ucp.server.api.KickApi {

    private final KickService kickService;
    private final KickMapper kickMapper;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Kick>> listAccountKicks(Integer accountId) {
        return ResponseEntity.ok(kickMapper.mapTo(kickService.getKicksByAccountId(accountId)));
    }
}
