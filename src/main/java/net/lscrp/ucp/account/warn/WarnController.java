package net.lscrp.ucp.account.warn;

import net.lscrp.ucp.server.api.WarnApi;
import net.lscrp.ucp.server.model.Warn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WarnController implements WarnApi {

    private final WarnService warnService;
    private final WarnMapper warnMapper;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Warn>> listAccountWarns(Integer accountId) {
        return ResponseEntity.ok(warnMapper.mapTo(warnService.getWarnsByAccountId(accountId)));
    }
}
