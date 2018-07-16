package com.example.uninotice.controller;

import com.example.uninotice.model.users.User;
import com.example.uninotice.security.user.CustomeUserDetailsService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class registrationController {
    
    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;
    
    @GetMapping("/registration")
    public String registration(Model model){
        
    	model.addAttribute("title", "Registration");
        model.addAttribute("user", new User());
        return "registration";
    }
    
    @PostMapping("/registration")
    public String registrationProcessing(@Valid User user, BindingResult bindingResult, Model model){
        
        if(bindingResult.hasErrors())
        {
            return "registration";
        }
        if(customeUserDetailsService.isUserPresent(user.getUsername()))
        {
            model.addAttribute("exists", "The username already exists");
            return "registration";
        }
        
        BCryptPasswordEncoder bCryptPasswordEncoder = new  BCryptPasswordEncoder();
        
        
        User user1 = new User();
        user1.setFirstname(user.getFirstname());
        user1.setSecondname(user.getSecondname());
        user1.setThirdname(user.getThirdname());
        user1.setUsername(user.getUsername());
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user1.setGender(user.getGender());
        user1.setUniversity(user.getUniversity());
        user1.setUniversityBrunch(user.getUniversityBrunch());
        user1.setCategory(user.getCategory());
        user1.setEmail(user.getEmail());
        
        
        customeUserDetailsService.addUser(user1);
        
        model.addAttribute("success", "You have successfull registered");
        
        return "/login";
    }

}
