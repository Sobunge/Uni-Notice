package com.example.uninotice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.uninotice.model.community.Community;
import com.example.uninotice.security.community.CommunityService;

@Controller
public class CommunityController {

	@Autowired
	CommunityService communityService;
	
	@RequestMapping("community")
	public String community(Model model) {
		
		ArrayList<Community> communities  = new ArrayList<>();
		
		communities.addAll(communityService.getAllCommunities());
		
		model.addAttribute("communities", communities);
		model.addAttribute("title", "Uni-Notice");
	
		
		return "community";
	}
}
