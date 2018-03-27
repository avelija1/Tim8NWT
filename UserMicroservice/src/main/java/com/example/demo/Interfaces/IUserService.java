package com.example.demo.Interfaces;

import java.util.List;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;

public interface IUserService {
	 	User getUser(long id);
        
	    void createUser(User user);
	     
	    void editUser(long id, User modifiedUser);
	     
	    void deleteUser(long id);
	 
	    List<User> getUsers(); 
	    
	    
	    Role getRole(long id);
        
	    void createRole(Role role);
	     
	    void editRole(long id, Role modifiedRole);
	     
	    void deleteRole(long id);
	 
	    List<Role> getRoles(); 
	   
	     
	  
}
