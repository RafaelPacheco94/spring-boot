package org.example.configuration;

import org.example.util.AuthenticationProviderUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private AuthenticationProviderUtil authenticationProviderUtil;

    public JwtAuthFilter(AuthenticationProviderUtil authenticationProviderUtil) {
        this.authenticationProviderUtil = authenticationProviderUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] authenticationElements = header.split(" ");
            if (authenticationElements.length == 2
                    && authenticationElements[0].equals("Bearer")) {
                try {
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    authenticationProviderUtil
                                            .validateToken(authenticationElements[1])
                            );
                } catch (RuntimeException exception) {
                    SecurityContextHolder
                            .clearContext();
                    throw exception;
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
