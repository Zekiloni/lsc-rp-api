package net.lscrp.ucp.account.warn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarnService {

    private final WarnRepository warnRepository;

    List<WarnEntity> getWarnsByAccountId(Integer accountId) {
        return warnRepository.getWarnsByAccountId(accountId);
    }
}
