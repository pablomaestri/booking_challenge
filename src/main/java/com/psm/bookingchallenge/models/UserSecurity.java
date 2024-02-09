package com.psm.bookingchallenge.models;

import org.springframework.security.core.GrantedAuthority;
import java.util.List;

public class UserSecurity {

    private String id;
    private String email;
    private String uPassword;
    private List<GrantedAuthority> uAuthorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public List<GrantedAuthority> getuAuthorities() {
        return uAuthorities;
    }

    public void setuAuthorities(List<GrantedAuthority> uAuthorities) {
        this.uAuthorities = uAuthorities;
    }
}
