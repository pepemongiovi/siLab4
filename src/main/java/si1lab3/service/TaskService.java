package si1lab3.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si1lab3.model.Task;
import si1lab3.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public void deleteAllTasks() {
		this.taskRepository.deleteAll();
	}
	
	public Task saveInDB(Task task) {
		return taskRepository.save(task);
	}
	
	public Task getByID(Long id) {
		return this.taskRepository.findOne(id);
	}
	
	public Collection<Task> getTasks() {
		return this.taskRepository.findAll();
	}
	
	public void deleteTask(Task task) {
		this.taskRepository.delete(task);
	}
}

