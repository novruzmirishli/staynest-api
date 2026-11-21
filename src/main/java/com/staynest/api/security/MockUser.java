package com.staynest.api.security;

public class MockUser {
    
    private String userId;
    private String role;
    
    public MockUser(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getRole() {
        return role;
    }
   
}