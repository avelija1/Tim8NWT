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
		private   UserRepository userRepository;
		private   RoleRepository roleRepository;
		
		 public UserService(UserRepository ur,RoleRepository rr)

		 {
			 this.userRepository=ur;
			 this.roleRepository=rr;
		 }
		 
	    private static List<User> users;
	    private static List<Role> roles;
	@Override
	public User getUser(long id) {
		users=(List<User>)userRepository.findAll();
		 for(User user : users){
	            if(user.getId() == id){
	                return user;
	            }
	        }
	        return null;
	}

	@Override
	public void createUser(User user) {
		   //user.setId(counter.incrementAndGet());
		   userRepository.save(user);
	}

	@Override
	public void editUser(Long id,User modifiedUser) {
		User user=userRepository.findOne(id);
		user.setFirstName(modifiedUser.getFirstName());
		user.setLastName(modifiedUser.getLastName());
		user.setSemester(modifiedUser.getSemester());
		user.setYear(modifiedUser.getYear());
		user.setEmail(modifiedUser.getEmail());
		user.setPasswordHash(modifiedUser.getPasswordHash());
		user.setRole(modifiedUser.getRole());
		user.setCourses(modifiedUser.getCourses());
       userRepository.save(user);
		
	}

	@Override
	public void deleteUser(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                userRepository.delete(id);
            }
        }
		
	}

	@Override
	public List<User> getUsers() {
		return (List<User>)userRepository.findAll();
	}
	
	 
	@Override
	public Role getRole(long id) {
		roles=(List<Role>)roleRepository.findAll();
		for(Role role : roles){
            if(role.getId() == id){
                return role;
            }
        }
        return null;
	}

	@Override
	public void createRole(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void editRole(long id, Role modifiedRole) {
		Role role=roleRepository.findOne(id);
		role.setName(modifiedRole.getName());
		roleRepository.save(role);
	}

	@Override
	public void deleteRole(long id) {
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext(); ) {
            Role role = iterator.next();
            if (role.getId() == id) {
            	roleRepository.delete(id);
            }
        }
	}

	@Override
	public List<Role> getRoles() {
		return (List<Role>)roleRepository.findAll();
	}

}
