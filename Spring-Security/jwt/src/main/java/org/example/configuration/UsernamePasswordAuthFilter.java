package org.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AuthenticationRequestDto;
import org.example.service.AuthenticationService;
import org.example.util.AuthenticationProviderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final AuthenticationProviderUtil authenticationProviderUtil;

    public UsernamePasswordAuthFilter(AuthenticationProviderUtil authenticationProviderUtil) {
        this.authenticationProviderUtil = authenticationProviderUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        if ("/api/v1/authentication".equals(httpServletRequest.getServletPath())
                && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
            AuthenticationRequestDto authenticationRequestDto = mapper.readValue(
                    httpServletRequest.getInputStream(), AuthenticationRequestDto.class);
            try {
                SecurityContextHolder.getContext().setAuthentication(
                        authenticationProviderUtil.validateCredentials(authenticationRequestDto)
                );
            } catch (RuntimeException exception) {
                SecurityContextHolder.clearContext();
                throw exception;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
