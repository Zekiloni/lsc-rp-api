package net.lscrp.ucp.account.password.reset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetEntity, Integer> {

    Optional<PasswordResetEntity> findByToken(String token);
}
