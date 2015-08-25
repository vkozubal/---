package org.pti.poster.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
@TypeAlias("user")
public class Person extends AbstractDocument implements UserDetails {
    private String name;
    private String password;
    private boolean active = true;

    @DBRef
    private Collection<Post> posts = new HashSet<>();

    private Set<Constants.SECURITY> roles = new HashSet<>();

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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

    
    public void addPost(Post post){
      getPosts().add(post);  
    }

    public void addPostAll(Collection<Post> postsToAdd){
        getPosts().addAll(postsToAdd);
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
        return active;
    }
}
