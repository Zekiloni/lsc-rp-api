package com.crp.ucp.account.ban;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BanService {

    private final BanRepository banRepository;

    public BanEntity createBan(BanEntity ban) {
        ban.setCreatedAt(OffsetDateTime.now());
        return banRepository.save(ban);
    }

    public List<BanEntity> getBansByAccountId(Long accountId) {
        return banRepository.getBansByAccountId(accountId);
    }
}
