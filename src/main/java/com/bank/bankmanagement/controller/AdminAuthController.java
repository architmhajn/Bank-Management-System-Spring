package com.bank.bankmanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankmanagement.security.JwtUtil;

@RestController
@RequestMapping("/api/admin/auth")
public class AdminAuthController {

    // Demo credentials — replace with a real admin store/DB table before production use.
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @PostMapping("/login")
    public Object login(@RequestBody AdminCredentials credentials) {

        if (ADMIN_USERNAME.equals(credentials.getUsername())
                && ADMIN_PASSWORD.equals(credentials.getPassword())) {

            String token = JwtUtil.generateToken("ROLE_ADMIN");
            return new TokenResponse(token);
        }

        return new ErrorResponse("Invalid admin credentials");
    }

    public static class AdminCredentials {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class TokenResponse {
        private final String token;
        public TokenResponse(String token) { this.token = token; }
        public String getToken() { return token; }
    }

    public static class ErrorResponse {
        private final String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
    }
}
