package com.project.urban.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.urban.DTO.TaskDTO;

@Service
public interface TaskService {

	List<TaskDTO> getAllTask();
	
	TaskDTO createTask (TaskDTO taskDTO);
	
	void deleteTask(Long taskId);
}
