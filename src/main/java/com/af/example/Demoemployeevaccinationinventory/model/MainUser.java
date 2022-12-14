/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Ismael
 */
public class MainUser implements UserDetails{
    private String userName;
    private String userPassword;
    private Person person;
    private Collection<? extends GrantedAuthority> authorities;

    public MainUser(String userName, String userPassword, Person person, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.person = person;
        this.authorities = authorities;
    }
    
    public static MainUser build(User user){
        List<GrantedAuthority> authorities=
                user.getRoles().stream().map(role->new SimpleGrantedAuthority(
                role.getRoleName().name())).collect(Collectors.toList());
        return new MainUser(user.getUserName(), user.getUserPassword(),user.getPerson(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    
}
