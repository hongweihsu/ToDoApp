package com.todoapp.hongwei.todoapp.repository;

import com.todoapp.hongwei.todoapp.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByCompleted(Boolean completed);

}
