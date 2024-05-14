package com.jfreed.expensestracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jfreed.expensestracker.register.RegistrationRequest;
import com.jfreed.expensestracker.user.Role;
import com.jfreed.expensestracker.user.User;
import com.jfreed.expensestracker.user.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/register")
    public String index(Model model) {
        RegistrationRequest request = new RegistrationRequest();
        model.addAttribute("request", request);
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
        @ModelAttribute("request") RegistrationRequest request,
        BindingResult result,
        Model model)
    {
        
        User user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .role(Role.USER)
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
        userRepository.save(user);
        return "redirect:/login?registerSuccess";
    }
    
    
}
