package si1lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import si1lab3.model.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

}