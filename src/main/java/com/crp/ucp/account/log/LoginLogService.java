package com.crp.ucp.account.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;

    public LoginLogEntity createLoginLog(LoginLogEntity loginLog) {
        return loginLogRepository.save(loginLog);
    }

    public List<LoginLogEntity> getByAccountId(Integer accountId) {
        return loginLogRepository.findByAccountId(accountId);
    }
}
