package si1lab3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collection;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import si1lab3.model.Task;
import si1lab3.model.ToDoList;
import si1lab3.service.ToDoListService;

@CrossOrigin(origins="*")
@RestController
public class ToDoListCtrl {
	
	@Autowired
	ToDoListService toDoListService;
		
	@RequestMapping(method=RequestMethod.GET, value="/toDoList/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ToDoList> getToDoListByID(@PathVariable Long id) {
		
		ToDoList toDoList = toDoListService.getByID(id);
		
		if(toDoList == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(toDoList, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/toDoList", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ToDoList>> getToDoLists() {
		
		Collection<ToDoList> toDoList = toDoListService.getLists();
		
		return new ResponseEntity<>(toDoList, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/toDoList/{id}")
	public ResponseEntity<Task> deleteListByID(@PathVariable Long id) {
		
		ToDoList toDoList = toDoListService.getByID(id);
		
		if(toDoList == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		toDoListService.deleteList(toDoList);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/toDoList", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ToDoList> registerToDoList(@RequestBody ToDoList toDoList) {
		
		ToDoList registeredToDoList = toDoListService.saveInDB(toDoList);
		
		return new ResponseEntity<>(registeredToDoList, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/toDoList", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ToDoList> editToDoList(@RequestBody ToDoList newToDoList) {
		
		ToDoList toDoList = toDoListService.saveInDB(newToDoList);
		return new ResponseEntity<>(toDoList, HttpStatus.OK);
	}
}
