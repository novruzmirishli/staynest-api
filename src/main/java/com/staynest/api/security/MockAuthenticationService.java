package com.staynest.api.security;

import org.springframework.stereotype.Service;

@Service 
public class MockAuthenticationService {
    
    public MockUser authenticate(String token) {

        if ("Bearer user-a-token".equals(token)) {
            return new MockUser("user-a", "USER");
        }
        if ("Bearer user-b-token".equals(token)) {
            return new MockUser("user-b", "USER");
        }
        if ("Bearer admin-token".equals(token)) {
            return new MockUser("admin", "ADMIN");
        }

        return null;
    }
}
