package com.locationvoiture.location_voiture.web.security;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.locationvoiture.location_voiture.dao.entities.User;



public class CustomUserDetail implements UserDetails {

    private User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert UserRole to GrantedAuthority
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        return Collections.singletonList(authority);
    }
    

    public Long getUserId() {
        return user.getId();
    }


    public String getname() {
        return user.getUsername();
    }
    

    

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
