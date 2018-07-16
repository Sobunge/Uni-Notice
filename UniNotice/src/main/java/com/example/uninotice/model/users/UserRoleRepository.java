package com.example.uninotice.model.users;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, String>{
    
    public List<String> findRoleByUsername(String username);
}
