package com.example.demo.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaces.IUserService;
import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;

@Service("userService")
@Transactional
public class UserService implements IUserService {
	 private static final AtomicLong counter = new AtomicLong();
	 
	    private static List<User> users;
	    private static List<Role> roles;
	    static{
	        users= populateDummyUsers();
	      //  roles= null;
	    }
	    private static List<User> populateDummyUsers(){
	        List<User> users = new ArrayList<User>();
	        users.add(new User(counter.incrementAndGet(),"Almedin","Velija",null,null,null,null,0,0));
	        users.add(new User(counter.incrementAndGet(),"Amer","Kodzaga",null,null,null,null,0,0));
	        return users;
	    }
	@Override
	public User getUser(long id) {
		 for(User user : users){
	            if(user.getId() == id){
	                return user;
	            }
	        }
	        return null;
	}

	@Override
	public void createUser(User user) {
		   user.setId(counter.incrementAndGet());
		   users.add(user);
		
	}

	@Override
	public void editUser(long id, User modifiedUser) {
		int index = users.indexOf(modifiedUser);
        users.set(index, modifiedUser);
		
	}

	@Override
	public void deleteUser(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
		
	}

	@Override
	public List<User> getUsers() {
		return users;
	}
	
	 
	@Override
	public Role getRole(long id) {
		for(Role role : roles){
            if(role.getId() == id){
                return role;
            }
        }
        return null;
	}

	@Override
	public void createRole(Role role) {
		role.setId(counter.incrementAndGet());
		   roles.add(role);
	}

	@Override
	public void editRole(long id, Role modifiedRole) {
		
		int index = roles.indexOf(modifiedRole);
        roles.set(index, modifiedRole);
	}

	@Override
	public void deleteRole(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
	}

	@Override
	public List<Role> getRoles() {
		return roles;
	}

}
