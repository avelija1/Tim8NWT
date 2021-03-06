package com.example.demo.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired; //
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Interfaces.ITaskService;

import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Repositories.TaskRepository;
import com.example.demo.Repositories.UserRepository;

@Service("taskService")
@Transactional
public class TaskService implements ITaskService {

	private static final AtomicLong counter = new AtomicLong();

	private TaskRepository taskRepository;
	private UserRepository userRepository;

	public TaskService(TaskRepository tr, UserRepository ur) {
		this.taskRepository = tr;
		this.userRepository = ur;
	}

	private static List<Task> tasks;

	@Override
	public Task getTask(long id) {
		tasks = (List<Task>) taskRepository.findAll();
		for (Task task : tasks) {
			if (task.getId() == id) {
				return task;
			}
		}
		return new Task("Zad1", "bilješke", new Date(), true, null);
	}

	@Override
	public void createTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void editTask(long id, Task modifiedTask) {
		Task task = taskRepository.findOne(id);
		task.setName(modifiedTask.getName());
		task.setNotes(modifiedTask.getNotes());
		task.setStatus(modifiedTask.getStatus());
		task.setUser(modifiedTask.getUser());
		task.setDate(modifiedTask.getDate());
		taskRepository.save(task);
	}

	@Override
	public void deleteTask(long id) {
		for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext();) {
			Task task = iterator.next();
			if (task.getId() == id) {
				taskRepository.delete(id);
			}
		}
	}

	@Override
	public List<Task> getTasks() {
		return (List<Task>) taskRepository.findAll();
	}

}
