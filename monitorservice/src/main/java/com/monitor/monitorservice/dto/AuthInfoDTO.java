package com.monitor.monitorservice.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;

public class AuthInfoDTO {
    public AuthInfoDTO(String token, Object role) {
        this.token = token;
        this.role = role;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private  String token;
    private  Object role;
}
