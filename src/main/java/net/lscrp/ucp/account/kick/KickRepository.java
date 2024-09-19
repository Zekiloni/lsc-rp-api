package net.lscrp.ucp.account.kick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KickRepository extends JpaRepository<KickEntity, Integer> {

    List<KickEntity> findByAccountId(Integer accountId);
}
