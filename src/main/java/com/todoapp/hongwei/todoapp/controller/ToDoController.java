package com.todoapp.hongwei.todoapp.controller;

import com.todoapp.hongwei.todoapp.dto.CreateToDoDto;
import com.todoapp.hongwei.todoapp.dto.ToDoDto;
import com.todoapp.hongwei.todoapp.dto.UpdateToDoDto;
import com.todoapp.hongwei.todoapp.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private ToDoService toDoService;
    public  ToDoController (ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @PostMapping("")
    public ResponseEntity<ToDoDto> createToDo(@RequestBody CreateToDoDto newToDo) {
        ToDoDto toDoDTO = toDoService.createToDo(newToDo);
        return new ResponseEntity<>(toDoDTO, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<ToDoDto> getToDos(@RequestParam Optional<Boolean> completed) {
        if (completed.isPresent()){
            return toDoService.getToDos(completed.get());
        }
        return toDoService.getToDos();
    }

    @GetMapping("/{id}")
    public ToDoDto getToDoById(@PathVariable Long id){
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDoDto updateToDo(@PathVariable Long id, @RequestBody UpdateToDoDto updateToDo){
        return toDoService.updateToDo(id, updateToDo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id){
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
