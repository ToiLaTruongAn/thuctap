package com.project.urban.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.urban.DTO.EventDTO;
import com.project.urban.Entity.Event;
import com.project.urban.Entity.Task;
import com.project.urban.Repository.EventRepository;
import com.project.urban.Repository.TaskRepository;
import com.project.urban.Service.EventService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public EventDTO createEvent(EventDTO eventDTO) {
		Task task = taskRepository.findById(eventDTO.getTaskId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
		ModelMapper modelMapper = new ModelMapper();
		Event event = modelMapper.map(eventDTO, Event.class);
//        eventDTO.setMaNV(event.getUser().getMaNV());
//        eventDTO.setName(event.getUser().getName());
//        eventDTO.setCompany(event.getUser().getCompany().getName());
        event.setTask(task);
		/*
		 * Long currentUserId = userService.getCurrentUserId(); User user =
		 * userRepository.findById(currentUserId) .orElseThrow(() -> new
		 * EntityNotFoundException("User not found")); e.setUser(user);
		 */
		Event saveEvent = eventRepository.save(event);
		EventDTO saveEventDTO = modelMapper.map(saveEvent, EventDTO.class);
		saveEventDTO.setTaskId(saveEvent.getTask().getId());

		return saveEventDTO;
	}

	@Override
	public void deleteEvent(Long eventId) {
		Event e = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
		eventRepository.deleteById(e.getId());
	}

	@Override
	public EventDTO updateEvent(EventDTO eventDTO) {
		Event existingEvent = eventRepository.findById(eventDTO.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(eventDTO, eventDTO);
		Event updatedEvent = eventRepository.save(existingEvent);
		EventDTO updatedEventDTO = modelMapper.map(updatedEvent, eventDTO.getClass());
		return updatedEventDTO;
	}

	@Override
	public List<EventDTO> getAllEvents() {
		List<EventDTO> allEvents = new ArrayList<>();
		List<Event> events = eventRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (Event event : events) {
			EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
	        eventDTO.setMaNV(event.getUser().getMaNV());
	        eventDTO.setName(event.getUser().getName());
	        eventDTO.setCompany(event.getUser().getCompany().getName());
			allEvents.add(eventDTO);
		}
		return allEvents;
	}
	
	
}
