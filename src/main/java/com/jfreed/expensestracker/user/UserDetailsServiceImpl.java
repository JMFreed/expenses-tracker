package com.jfreed.expensestracker.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> option = repository.findByEmail(email);
        if (!option.isPresent()) {
            throw new UsernameNotFoundException("User not found %s".formatted(email));
        }
        var user = option.get();
        return User.builder()
            .email(user.getEmail())
            .password(user.getPassword())
            .role(user.getRole())
            .build();
    }
    
}
