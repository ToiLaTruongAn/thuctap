package com.project.urban.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

@Data
public class EventDTO {
	
	private Long id;
	
	private String MaNV;
	
	private String name;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime start;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime end;
	
	private String text;
	
	private String company;
	
	private String bussiness_trip;
	
	private Long taskId;
	
	private Long userId;
}
