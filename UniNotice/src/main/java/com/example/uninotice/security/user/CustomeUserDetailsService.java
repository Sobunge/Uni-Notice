package com.example.uninotice.security.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.uninotice.model.users.UserRepository;
import com.example.uninotice.model.users.UserRoleRepository;
import com.example.uninotice.model.users.User;


@Service("CustomeUserDetailsService")
public class CustomeUserDetailsService implements UserDetailsService{

	
	
	private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    
    @Autowired
    public CustomeUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository){
        
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username);
        
        if(user.equals(null)){
            throw new UsernameNotFoundException("No user present with the username: " + username);
        }
        else
        {
            List<String> userRole = userRoleRepository.findRoleByUsername(username);
            
            return new CustomeUserDetails(user, userRole);
        }
        
    }
    
    public void addUser(User user) {
        userRepository.save(user);
    }
    
    public boolean isUserPresent(String username){
       
        return userRepository.existsById(username);
    }
 
    public User activeUser(String username) {
    	return userRepository.findByUsername(username);
    }
    
}
