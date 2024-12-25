package com.cts.SchoolManagementSystem.security;

import com.cts.SchoolManagementSystem.entity.User;
import com.cts.SchoolManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
         Optional<User> user= Optional.ofNullable(userRepository.findByEmail(username));
         return user.map(UserUserDetails::new)
                 .orElseThrow(()->new UsernameNotFoundException("user not found"));
     }
}
