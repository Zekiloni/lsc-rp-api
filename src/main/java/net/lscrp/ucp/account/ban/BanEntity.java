package net.lscrp.ucp.account.ban;

import net.lscrp.ucp.account.AccountEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "bans")
public class BanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Size(max = 128)
    @NotNull
    @Column(name = "reason", nullable = false, length = 128)
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "admin_account_id")
    private AccountEntity adminAccount;

    @Size(max = 45)
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "expiring_at")
    private LocalDate expiringAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public boolean getIsExpired() {
        return expiringAt != null && expiringAt.isBefore(LocalDate.now());
    }
}