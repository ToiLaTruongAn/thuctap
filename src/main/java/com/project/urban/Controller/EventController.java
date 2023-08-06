package com.project.urban.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.project.urban.DTO.EventDTO;
import com.project.urban.Entity.Event;
import com.project.urban.Repository.EventRepository;
import com.project.urban.Service.EventService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/events")
public class EventController {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private EventService eventService;

	@GetMapping("/")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
			@RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
		return eventRepository.findBetween(start, end);
	}

	@PostMapping("/create")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Transactional
	public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
		EventDTO createdEvent = eventService.createEvent(eventDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEvent(@PathVariable("id") Long eventId) {
		eventService.deleteEvent(eventId);
		return new ResponseEntity<>("Event successfully deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EventDTO> updateEvent(@PathVariable("id") Long eventId, @RequestBody EventDTO eventDTO) {
		eventDTO.setId(eventId);
		EventDTO updatedEventDTO = eventService.updateEvent(eventDTO);
		return new ResponseEntity<>(updatedEventDTO, HttpStatus.OK);
	}
	
	//search
	@GetMapping("/getAllEvent")
	public ResponseEntity<List<EventDTO>> getAllEvents() {
		List<EventDTO> events = eventService.getAllEvents();
		return new ResponseEntity<>(events, HttpStatus.OK);
	}
}
