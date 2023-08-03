package com.project.urban.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class PositionDTO {
	private Long id;
	private String name;
}
