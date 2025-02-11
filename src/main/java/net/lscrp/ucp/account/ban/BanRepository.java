package net.lscrp.ucp.account.ban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BanRepository extends JpaRepository<BanEntity, Integer> {

    List<BanEntity> getBansByAccountId(Integer accountId);
}
