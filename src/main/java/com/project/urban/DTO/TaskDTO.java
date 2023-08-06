package com.project.urban.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class TaskDTO {
	private Long id;
	private String name;

}
