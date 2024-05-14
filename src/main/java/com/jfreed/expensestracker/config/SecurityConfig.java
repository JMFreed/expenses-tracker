package com.jfreed.expensestracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.jfreed.expensestracker.auth.AuthenticationSuccessHandlerImpl;
import com.jfreed.expensestracker.user.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(registry -> {
                registry.requestMatchers("/home").permitAll();
                registry.requestMatchers("/admin/**").hasRole("ADMIN");
                registry.requestMatchers("/user/**").hasRole("USER");
                registry.anyRequest().authenticated();
            })
            .formLogin(formLogin -> {
                formLogin.successHandler(authenticationSuccessHandler());
            })
            .build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new AuthenticationSuccessHandlerImpl();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
