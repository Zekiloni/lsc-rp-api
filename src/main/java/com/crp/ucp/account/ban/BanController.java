package com.crp.ucp.account.ban;

import com.crp.ucp.server.api.BanApi;
import com.crp.ucp.server.model.Ban;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BanController implements BanApi {

    private final BanService banService;
    private final BanMapper banMapper;

    @Override
    public ResponseEntity<List<Ban>> listAccountBans(Integer accountId) {
        return ResponseEntity.ok(banMapper.mapTo(banService.getBansByAccountId(accountId)));
    }

    @Override
    public ResponseEntity<List<Ban>> listBans() {
        return ResponseEntity.ok(banMapper.mapTo(banService.getAllBans()));
    }
}
