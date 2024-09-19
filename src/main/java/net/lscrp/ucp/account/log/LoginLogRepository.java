package net.lscrp.ucp.account.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLogEntity, Integer> {

    List<LoginLogEntity> findByAccountId(Integer accountId);
}
