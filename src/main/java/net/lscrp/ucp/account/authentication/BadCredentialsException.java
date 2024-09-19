package net.lscrp.ucp.account.authentication;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException() {
        super("Pogrešna korisnička šifra");
    }
}
