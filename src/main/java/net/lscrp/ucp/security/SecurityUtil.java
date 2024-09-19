package net.lscrp.ucp.security;

import net.lscrp.ucp.account.RoleType;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SecurityUtil {

    public static SimpleGrantedAuthority createSimpleGrantedAuthority(RoleType role) {
        return new SimpleGrantedAuthority(role.getValue());
    }

    public static ArrayList<SimpleGrantedAuthority> createDefaultGrantedAuthority() {
        return new ArrayList<>(List.of(createSimpleGrantedAuthority(RoleType.USER_ROLE)));
    }
}
