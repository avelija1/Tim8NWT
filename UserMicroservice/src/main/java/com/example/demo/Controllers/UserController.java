package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {

		List<User> users = userService.getUsers();
		users.add(new User("Selma", "Glavić", "sgl1", "sgl1@etf.unsa.ba", "sifra", 1, 2));

		if (users.isEmpty()) {

			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);

		User user = userService.getUser(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

		userService.createUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {

		User currentUser = userService.getUser(id);

		if (currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.editUser(id, user);

		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userService.getUser(id);

		if (user == null) {

			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUser(id);

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/role/", method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getRoles() {

		List<Role> roles = userService.getRoles();

		if (roles.isEmpty()) {
			return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> getRole(@PathVariable("id") long id) {
		System.out.println("Fetching Role with id " + id);

		Role role = userService.getRole(id);

		if (role == null) {
			System.out.println("Role with id " + id + " not found");
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value = "/role/", method = RequestMethod.POST)
	public ResponseEntity<Void> createRole(@RequestBody Role role, UriComponentsBuilder ucBuilder) {

		userService.createRole(role);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/role/{id}").buildAndExpand(role.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Role> updateUser(@PathVariable("id") long id, @RequestBody Role role) {
		System.out.println("Updating Role " + id);

		Role currentRole = userService.getRole(id);

		if (currentRole == null) {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}

		userService.editRole(id, role);

		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Role> deleteRole(@PathVariable("id") long id) {

		Role role = userService.getRole(id);

		if (role == null) {
			System.out.println("Unable to delete. Role with id " + id + " not found");
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}

		userService.deleteRole(id);

		return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
	}

}