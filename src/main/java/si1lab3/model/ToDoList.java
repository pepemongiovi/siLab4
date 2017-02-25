package si1lab3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TO_DO_LIST")
public class ToDoList {
	
	@Id
	@Column(name="TO_DO_LIST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	public ToDoList(){
		//DEFAULT
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ToDoList(String description){
		this.description = description;
		this.tasks = new ArrayList<Task>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
}
