package com.example.uninotice.security.community;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.uninotice.model.community.Community;
import com.example.uninotice.model.community.CommunityRepository;


@Service
public class CommunityService{

	@Autowired
	CommunityRepository communityRepository;
	
	
	public List<Community> getAllCommunities(){
		
		List<Community> community =new ArrayList<>();
		
		communityRepository.findAll().forEach(community::add);
		
		return community;
	}
	
	public void addCommunity(Community community) {
		communityRepository.save(community);
	}
	
	public Optional<Community> getCommunity(long id){

		return communityRepository.findById(id);
		
	}
	
	public boolean isCommunityPresent(String name) {
	
		return communityRepository.existsByName(name);
	}

}
