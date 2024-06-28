package com.crp.ucp.account.kick;

import com.crp.ucp.server.api.KickApi;
import com.crp.ucp.server.model.Kick;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KickController implements KickApi {

    private final KickService kickService;
    private final KickMapper kickMapper;

    @Override
    public ResponseEntity<List<Kick>> listAccountKicks(Integer accountId) {
        return ResponseEntity.ok(kickMapper.mapTo(kickService.getKicksByAccountId(accountId)));
    }
}
