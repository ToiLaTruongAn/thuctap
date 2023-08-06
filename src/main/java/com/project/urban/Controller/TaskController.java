package com.project.urban.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.urban.DTO.TaskDTO;
import com.project.urban.Service.TaskService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@PostMapping("/")
	public ResponseEntity<TaskDTO> createTask(@Valid TaskDTO taskDTO, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task data");
	    }
		TaskDTO savedTaskDTO = taskService.createTask(taskDTO);
        
        return new ResponseEntity<>(savedTaskDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
    public ResponseEntity<List<TaskDTO>> getAllTask(){
    	List<TaskDTO> tasks = taskService.getAllTask();
    	return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
    	taskService.deleteTask(taskId);
    	return new ResponseEntity<>("task successfully deleted!", HttpStatus.OK);
    }
}
