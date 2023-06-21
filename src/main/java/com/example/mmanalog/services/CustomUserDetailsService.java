package com.example.mmanalog.services;

import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.models.Authority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements
        UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService personService) {
        this.userService = personService;
    }

    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.getUserByUsername(username);
        String password = userDto.getPassword();
        Set<Authority> authorities = userDto.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }
}
