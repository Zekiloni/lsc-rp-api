package com.crp.ucp.account.authorization;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
        super("Wrong password");
    }
}
