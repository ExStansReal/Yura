package com.example.Yura.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER, BANANAADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}