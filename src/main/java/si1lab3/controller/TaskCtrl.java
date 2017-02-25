package si1lab3.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import si1lab3.model.Task;
import si1lab3.service.TaskService;

@CrossOrigin(origins="*")
@RestController
public class TaskCtrl {
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(method=RequestMethod.GET, value="/tasks/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		
		Task tasks = taskService.getByID(id);
		
		if(tasks == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/tasks", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> registerTask(@RequestBody Task task) {
		
		Task registeredTask = taskService.saveInDB(task);
		
		return new ResponseEntity<>(registeredTask, HttpStatus.CREATED);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value="/tasks", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Task>> getTasks() {
		
		Collection<Task> tasks = taskService.getTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/tasks", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> editTask(@RequestBody Task newTask) {
		
		Task task = taskService.saveInDB(newTask);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/tasks/{id}")
	public ResponseEntity<Task> removeTask(@PathVariable long id) {
		
		Task task = taskService.getByID(id);
		
		if(task==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		taskService.deleteTask(task);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	
}
