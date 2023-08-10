package com.project.urban.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.urban.DTO.EventDTO;

@Service
public interface EventService {

	EventDTO createEvent(EventDTO eventDTO);
	
	EventDTO updateEvent(EventDTO eventDTO);
	
	List<EventDTO> getAllEvents();

	void deleteEvent(Long eventId);
	
	//search 
    List<EventDTO> searchByMaNV(String MaNV);
    
    List<EventDTO> searchByName(String name);
    
    List<EventDTO> seachByCompany(String name);
    
    List<EventDTO> seachByTask(String name);


}
