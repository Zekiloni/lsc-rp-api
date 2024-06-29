package com.crp.ucp.account.warn;

import com.crp.ucp.server.api.WarnApi;
import com.crp.ucp.server.model.Warn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WarnController implements WarnApi {

    private final WarnService warnService;
    private final WarnMapper warnMapper;

    @Override
    public ResponseEntity<List<Warn>> listAccountWarns(Integer accountId) {
        return ResponseEntity.ok(warnMapper.mapTo(warnService.getWarnsByAccountId(accountId)));
    }
}
