package com.example.uninotice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.uninotice.model.community.Community;
import com.example.uninotice.security.community.CommunityService;

@Controller
public class CreateCommunityController {

	@Autowired
	CommunityService communityService;

	@GetMapping("createCommunity")
	public String createCommunity(Model model) {

		model.addAttribute("title", "Uni-Notice");
		model.addAttribute("community", new Community());

		return "createCommunity";
	}

	@PostMapping("createCommunity")
	public String processCommunityCreation(@Valid Community community, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "createCommunity";
		}
		if (communityService.isCommunityPresent(community.getName())) {
			model.addAttribute("exists", "The a community with the name " + community.getName() + " already exists!!");
			return "createCommunity";
		}

		try {

			communityService.addCommunity(community);
			model.addAttribute("title", "Uni-Notice");
			model.addAttribute("success", "You have successfully created a community");
			return "community";
		} catch (Exception e) {

			model.addAttribute("fail", "FAIL! Maybe You had uploaded the file before or the file's size > 500KB");
			return "createCommunity";

		}
	}

}
