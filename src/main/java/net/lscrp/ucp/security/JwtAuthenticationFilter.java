package net.lscrp.ucp.security;

import net.lscrp.ucp.account.AccountEntity;
import net.lscrp.ucp.account.AccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtAuthenticationService jwtAuthenticationService;
    private final AccountService accountService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (token == null || !token.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(BEARER_PREFIX.length());

        String username = jwtAuthenticationService.extractUsername(token);
        Integer accountId = jwtAuthenticationService.extractAccountId(token);

        if (username != null && accountId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtAuthenticationService.isTokenValid(token, username)) {
                Optional<AccountEntity> account = accountService.getAccountById(accountId);
                account.ifPresent(accountEntity -> setSecurityContextAuthentication(request, accountEntity));
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setSecurityContextAuthentication(HttpServletRequest request, AccountEntity account) {
        UsernamePasswordAuthenticationToken authenticationToken = createAuthenticationToken(account);
        setAuthenticationTokenDetails(request, authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private void setAuthenticationTokenDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authenticationToken) {
        WebAuthenticationDetails details = new WebAuthenticationDetailsSource()
                .buildDetails(request);

        authenticationToken.setDetails(details);
    }

    private static UsernamePasswordAuthenticationToken createAuthenticationToken(AccountEntity account) {
        return new UsernamePasswordAuthenticationToken(
                account, null, account.getAuthorities()
        );
    }
}