package si1lab3.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task{
	@Id
	@Column(name="TASK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="DETAILED_DESCRIPTION")
	private String detailedDescription;
	
	@Column(name="FINISHED")
	private boolean finished;
	
	@Column(name="PRIORITY")
	private String priority;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="SUBTASKS")
	private ArrayList<String> subtasks;
	
	public Task(){
		//DEFAULT
	}
	
	public Task(String description, String detailedDescription, String priority, String category){
		this.description = description;
		this.detailedDescription = detailedDescription;
		this.priority = priority;
		this.category = category;
		this.finished = false;
		this.subtasks = new ArrayList<String>();
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<String> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(ArrayList<String> subtasks) {
		this.subtasks = subtasks;
	}
	
	
	
}
