package com.crp.ucp.account.ban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BanRepository extends JpaRepository<BanEntity, Long> {

    List<BanEntity> getBansByAccountId(Long accountId);
}
