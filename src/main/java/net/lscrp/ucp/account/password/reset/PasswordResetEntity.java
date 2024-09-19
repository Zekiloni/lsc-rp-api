package net.lscrp.ucp.account.password.reset;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.lscrp.ucp.account.AccountEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "password_resets")
public class PasswordResetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private AccountEntity account;

    @Size(max = 128)
    @NotNull
    @Column(name = "token", nullable = false, length = 128)
    private String token;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @NotNull
    @Column(name = "expiring_at", nullable = false)
    private OffsetDateTime expiringAt;

    public boolean isExpired() {
        return OffsetDateTime.now().isAfter(expiringAt);
    }
}