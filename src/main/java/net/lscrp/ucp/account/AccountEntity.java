package net.lscrp.ucp.account;

import net.lscrp.ucp.character.CharacterEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.lscrp.ucp.faction.FactionEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static net.lscrp.ucp.config.SecurityUtil.createDefaultGrantedAuthority;
import static net.lscrp.ucp.config.SecurityUtil.createSimpleGrantedAuthority;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CharacterEntity> characters;

    @Column(nullable = false)
    private Integer admin;

    private Integer muted;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "last_login_at")
    private OffsetDateTime lastLoginAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Builder.Default
    @Column(name = "premium")
    private Integer premium = 0;

    @Builder.Default
    @Column(name = "premium_coins")
    private Integer premiumCoins = 0;

    public int getTotalHours() {
        int totalMinutes = characters.stream()
                .mapToInt(CharacterEntity::getMinutes)
                .sum();

        int totalHours = characters.stream()
                .mapToInt(CharacterEntity::getHours)
                .sum();

        totalHours += totalMinutes / 60;

        return totalHours;
    }

    public boolean isFactionLeader(FactionEntity faction) {
        return characters.stream()
                .filter(character -> Objects.equals(character.getFactionId(), faction.getId()))
                .anyMatch(CharacterEntity::getIsLeader);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = createDefaultGrantedAuthority();

        if (admin > 0) {
            grantedAuthorities.add(createSimpleGrantedAuthority(RoleType.ADMIN_ROLE));
            if (this.admin > 4) {
                grantedAuthorities.add(createSimpleGrantedAuthority(RoleType.SUPER_ADMIN));
            }
        }

        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
