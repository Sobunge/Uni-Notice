package com.example.uninotice.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.uninotice.model.users.User;
import com.example.uninotice.security.user.CustomeUserDetailsService;

@Controller
public class HomepageController {

	@Autowired
	CustomeUserDetailsService customeUserDetailsService;

	@RequestMapping(path = "/homepage", method = RequestMethod.GET)
	public String homepage(Model model, Principal principal) {

		User user = customeUserDetailsService.activeUser(principal.getName());

		model.addAttribute("title", "Uni-Notice");

		if (user == null) {
			return "redirect:/login?logout";
		} else {
			model.addAttribute("user", user);

			return "homepage";
		}
	}

}
