package com.crp.ucp.account.authentication;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException() {
        super("Wrong password");
    }
}
