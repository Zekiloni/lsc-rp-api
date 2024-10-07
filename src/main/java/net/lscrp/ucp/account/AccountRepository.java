package net.lscrp.ucp.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findByUsername(String username);

    Optional<AccountEntity> findByEmail(String email);

    Page<AccountEntity> findByUsernameContainingOrEmailContaining(String username, String email,
                                                                  Pageable pageable);

    Page<AccountEntity> findByUsernameContainingAndEmailContaining(String username, String email,
                                                                   Pageable pageable);

}
