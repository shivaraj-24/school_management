package com.cts.SchoolManagementSystem.security;

import com.cts.SchoolManagementSystem.entity.User;
//import lombok.Getter;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class UserUserDetails implements UserDetails {
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;
    public UserUserDetails(User user){
        name=user.getEmail();
        password=user.getPassword();
        authorities= Arrays.stream(user.getRole().split(" , "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        System.out.println(authorities);
        return authorities;
    }


    public String getUsername(){
        return name;
    }
}
