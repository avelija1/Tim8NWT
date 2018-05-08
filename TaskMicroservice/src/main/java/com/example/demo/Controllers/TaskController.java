package com.example.demo.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Services.TaskService;

@RestController
public class TaskController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	TaskService taskService;

	@RequestMapping(value = "/task/user/", method = RequestMethod.GET)
	public ResponseEntity<User> getUserFromTask() {
		List<ServiceInstance> instances = discoveryClient.getInstances("ZUUL-SERVICE");
		ServiceInstance serviceInstance = instances.get(0);

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/user/1";

		RestTemplate restTemplate = new RestTemplate();
		User response = null;
		try {
			response = restTemplate.getForObject(baseUrl, User.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return new ResponseEntity<User>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/task/", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getTasks() {
		List<Task> tasks = taskService.getTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> getTask(@PathVariable("id") long id) {
		System.out.println("Fethching Task with id " + id);
		Task task = taskService.getTask(id);
		if (task == null) {
			System.out.println("Task with id " + id + " not found");
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@RequestMapping(value = "/task/", method = RequestMethod.POST)
	public ResponseEntity<Void> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
		taskService.createTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
		Task currentTask = taskService.getTask(id);
		if (currentTask == null) {
			System.out.println("Task with id " + id + " not found");
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		taskService.editTask(id, task);
		return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Task> deleteTask(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Task with id " + id);
		Task task = taskService.getTask(id);
		if (task == null) {
			System.out.println("Unable to delete. Task with id " + id + " not found");
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		taskService.deleteTask(id);
		return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	}
}