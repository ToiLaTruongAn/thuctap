package com.project.urban.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.urban.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
}
