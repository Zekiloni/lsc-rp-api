package com.crp.ucp.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    USER_ROLE("user"),
    ADMIN_ROLE("admin"),
    SUPER_ADMIN("super_admin");

    private final String value;
}
