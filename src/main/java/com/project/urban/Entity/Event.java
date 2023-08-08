package com.project.urban.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "event_start")
	private LocalDateTime start;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "event_end")
	private LocalDateTime end;

	private String text;

	private String company;

	private String bussiness_trip;

	// relationship
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "Task_id", referencedColumnName = "id")
	private Task task;

	@ManyToOne
	@JoinColumn(name = "User_id", referencedColumnName = "id")
	private User user;

}
