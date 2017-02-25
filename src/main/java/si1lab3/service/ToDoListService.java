package si1lab3.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si1lab3.model.ToDoList;
import si1lab3.repository.ToDoListRepository;

@Service
public class ToDoListService {
	
	@Autowired
	private ToDoListRepository toDoListRepository;
	
	public ToDoList getByID(Long id) {
		return this.toDoListRepository.findOne(id);
	}
	
	public Collection<ToDoList> getLists() {
		return this.toDoListRepository.findAll();
	}
	
	public ToDoList saveInDB(ToDoList toDoList) {
		return toDoListRepository.save(toDoList);
	}
	
	public void deleteList(ToDoList toDoList) {
		this.toDoListRepository.delete(toDoList);
	}
	
}
