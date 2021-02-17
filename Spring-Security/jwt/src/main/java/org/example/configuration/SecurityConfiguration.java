package org.example.configuration;

import org.example.util.AuthenticationEntryPointExceptionHandler;
import org.example.util.AuthenticationProviderUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPointExceptionHandler authenticationEntryPointExceptionHandler;
    private final AuthenticationProviderUtil authenticationProviderUtil;

    public SecurityConfiguration(AuthenticationEntryPointExceptionHandler authenticationEntryPointExceptionHandler,
                                 AuthenticationProviderUtil authenticationProviderUtil) {
        this.authenticationEntryPointExceptionHandler = authenticationEntryPointExceptionHandler;
        this.authenticationProviderUtil = authenticationProviderUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPointExceptionHandler)

                .and()

                .addFilterBefore(new UsernamePasswordAuthFilter(authenticationProviderUtil), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthFilter(authenticationProviderUtil), UsernamePasswordAuthFilter.class)

                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/authentication", "/api/v1/registration")
                .permitAll()

                .anyRequest()
                .authenticated();
    }
}
