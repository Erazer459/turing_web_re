package com.yantailor.turing_web_re.entity.login;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yantailor
 * on 2022/3/22 18:29 @Version 1.0
 */
public class User implements UserDetails {


    private String memberEmail;

    private String memberPassword;

    private List<Role> roles = new ArrayList<>();


    public User() {
    }

    public User(String memberEmail, String memberPassword, List<Role> roles) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        for(Role role : roles){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }
    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberEmail;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
