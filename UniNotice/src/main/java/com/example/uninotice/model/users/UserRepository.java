package com.example.uninotice.model.users;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String >{
    
    public User findByUsername(String username);
    
}
