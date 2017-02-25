package si1lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import si1lab3.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
