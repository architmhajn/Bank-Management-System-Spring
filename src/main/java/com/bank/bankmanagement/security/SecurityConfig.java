package com.bank.bankmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public: account creation/login, and the admin login that issues the token
                .requestMatchers("/api/accounts", "/api/accounts/login").permitAll()
                .requestMatchers("/api/admin/auth/login").permitAll()
                // Everything else under /api/admin/** requires a valid ROLE_ADMIN token
                .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                // Account operations (deposit/withdraw/transactions) open for now —
                // tighten this once account-holder login also issues a token
                .anyRequest().permitAll()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
