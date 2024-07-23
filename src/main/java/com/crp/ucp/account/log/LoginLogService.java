package com.crp.ucp.account.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;

    public List<LoginLogEntity> getByAccountId(Integer accountId) {
        return loginLogRepository.findByAccountId(accountId);
    }
}
