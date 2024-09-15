package com.instagram.clone.security;

import com.instagram.clone.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // يمكنك تخصيص هذا حسب احتياجاتك
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // يمكنك تخصيص هذا حسب احتياجاتك
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // يمكنك تخصيص هذا حسب احتياجاتك
    }

    @Override
    public boolean isEnabled() {
        return true; // يمكنك تخصيص هذا حسب احتياجاتك
    }

    public User getUser() {
        return user;
    }
}
