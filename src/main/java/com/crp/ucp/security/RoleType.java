package com.crp.ucp.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {
    USER_ROLE("user"),
    ADMIN_ROLE("admin"),
    SUPER_ADMIN("super_admin");

    private final String value;
}
