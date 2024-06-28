package com.crp.ucp.account.kick;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KickService {

    private final KickRepository kickRepository;

    public List<KickEntity> getKicksByAccountId(Integer accountId) {
        return kickRepository.findByAccountId(accountId);
    }
}
