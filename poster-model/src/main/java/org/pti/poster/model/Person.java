package org.pti.poster.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Document
@Data
public class Person implements UserDetails {

    private String name;
    private String password;
    @Id
    private Long Id;
    private Collection<Post> posts = new HashSet<>();
    private Set<Constants.SECURITY> roles = new HashSet<>();

    protected Person() {
       /* Reflection instantiation */
    }

    public Person(String name, String passwordHash) {
        this.name = name;
        this.password = passwordHash;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<Constants.SECURITY> roles = this.getRoles();

        if (roles == null) {
            return Collections.emptyList();
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Constants.SECURITY role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.role));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.name;
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
