package com.project.urban.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.urban.DTO.TaskDTO;
import com.project.urban.Entity.Task;
import com.project.urban.Repository.TaskRepository;
import com.project.urban.Service.TaskService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDTO createTask(TaskDTO taskDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Task task = modelMapper.map(taskDTO, Task.class);
		Task savedTask = taskRepository.save(task);
		TaskDTO savedTaskDTO = modelMapper.map(savedTask, TaskDTO.class);
		return savedTaskDTO;
	}

	@Override
	public List<TaskDTO> getAllTask() {
		List<TaskDTO> allTask = new ArrayList<>();
		List<Task> tasks = taskRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (Task task : tasks) {
			TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
			allTask.add(taskDTO);
		}
		return allTask;
	}

	@Override
	public void deleteTask(Long taskId) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "position not found"));
		taskRepository.deleteById(task.getId());

	}
}
