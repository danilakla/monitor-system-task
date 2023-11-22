package com.monitor.monitorservice.security;

import com.monitor.monitorservice.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public UserPrincipal(Users user) {
       UserPrincipal userPrincipal= create(user);
        this.id=userPrincipal.id;
        this.authorities=userPrincipal.getAuthorities();
        this.username=userPrincipal.username;
        this.password=userPrincipal.password;
    }
    public UserPrincipal(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // геттеры и методы интерфейса UserDetails

    public static UserPrincipal create(Users user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(new ArrayList<String>(user.getRoles().stream().map(e->e.getName()).collect(Collectors.toList())).get(0)));

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}