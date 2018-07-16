package com.example.uninotice.model.community;

import org.springframework.data.repository.CrudRepository;


public interface CommunityRepository extends CrudRepository<Community, Long> {

	public boolean existsByName(String name);


}
