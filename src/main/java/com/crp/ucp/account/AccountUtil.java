package com.crp.ucp.account;

import lombok.experimental.UtilityClass;

import java.util.NoSuchElementException;

import static java.text.MessageFormat.format;

@UtilityClass
public class AccountUtil {

    public static NoSuchElementException throwAccountNotFoundException(Integer accountId) {
        return new NoSuchElementException(format("Račun sa ID-om {0} ne postoji", accountId));
    }

    public static NoSuchElementException throwAccountNotFoundException(String username) {
        return new NoSuchElementException(format("Račun sa imenom {0} ne postoji", username));
    }
}
