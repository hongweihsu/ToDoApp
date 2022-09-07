package com.todoapp.hongwei.todoapp.service;

import com.todoapp.hongwei.todoapp.dto.CreateToDoDto;
import com.todoapp.hongwei.todoapp.dto.ToDoDto;
import com.todoapp.hongwei.todoapp.dto.UpdateToDoDto;
import com.todoapp.hongwei.todoapp.exception.ToDoException;
import com.todoapp.hongwei.todoapp.model.ToDo;
import com.todoapp.hongwei.todoapp.repository.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    public ToDoDto createToDo(CreateToDoDto createToDoDto){
        ToDo newToDo = new ToDo();
        newToDo.setName(createToDoDto.getName());
        newToDo.setCompleted((createToDoDto.getCompleted()));
        ToDo toDo = toDoRepository.save(newToDo); // 所以save會回傳剛存進去的？
        return new ToDoDto(toDo); // 新創造的
    }

    public List<ToDoDto> getToDos(){
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream().map(entity -> new ToDoDto(entity)).collect(Collectors.toList());
    }

    public List<ToDoDto> getToDos(Boolean completed){
        List<ToDo> toDos = toDoRepository.findByCompleted(completed);
        return toDos.stream().map(entity -> new ToDoDto(entity)).collect(Collectors.toList());
    }

    public ToDoDto getToDoById(Long id){
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()){
            return new ToDoDto(toDo.get());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "getToDoById - to do not found");
        }
    }

    public ToDoDto updateToDo(Long id, UpdateToDoDto updateToDo){
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if(toDo.isPresent()){
            toDo.get().setName(updateToDo.getName());
            toDo.get().setCompleted(updateToDo.getCompleted());
            toDoRepository.save(toDo.get());
            return new ToDoDto(toDo.get());
        }else {
            throw new ToDoException(404, "updateToDo - to do not found");
        }
    }

    public void deleteToDo(Long id){
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if(toDo.isPresent()){
            toDoRepository.delete(toDo.get());
        }else {
            throw new RuntimeException("deleteToDo - to do not fund");
        }
    }


}
