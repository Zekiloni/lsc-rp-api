package com.crp.ucp.account.warn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarnRepository extends JpaRepository<WarnEntity, Integer> {

    List<WarnEntity> getWarnsByAccountId(Integer accountId);
}
