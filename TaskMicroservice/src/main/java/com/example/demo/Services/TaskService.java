package com.example.demo.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired; //

import org.springframework.stereotype.Service;
import com.example.demo.Interfaces.ITaskService; 

import com.example.demo.Models.Task;
import com.example.demo.Models.User;

@Service("taskService")
@Transactional
public class TaskService implements ITaskService {
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Task> tasks;
	
	static {
		tasks = populateDummyTasks();
	}
	private static List<Task> populateDummyTasks(){
		List<Task> tasks = new ArrayList<Task>();
		/*User user1 = new User("Lejla", "Bajgorić");
		Date datum = new Date();
		tasks.add(new Task(counter.incrementAndGet(), "zadatak1", "Bilješke", datum, true, user1));*/
		return tasks;
	}
	
	@Override
	public Task getTask(long id) {
		for (Task task : tasks) {
			if (task.getId() == id) {
				return task;
			}
		}
		return null;
	}

	@Override
	public void createTask(Task task) {
		task.setId(counter.incrementAndGet());
		tasks.add(task);
		
	}

	@Override
	public void editTask(long id, Task modifiedTask) {
		int index = tasks.indexOf(modifiedTask);
		tasks.set(index, modifiedTask);
		
	}

	@Override
	public void deleteTask(long id) {
		for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext(); ) {
			Task task = iterator.next();
			if (task.getId() == id) {
				iterator.remove();
			}
		}
				
		
	}

	@Override
	public List<Task> getTasks() {
		return tasks;
	}
	
	
	
	
}
