package com.project.urban.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.urban.DTO.PositionDTO;
import com.project.urban.Service.PositionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/positions")
public class PositionController {
	
	@Autowired
	private PositionService positionService;
	
	@PostMapping("/")
	public ResponseEntity<PositionDTO> createPosition(@Valid PositionDTO positionDTO, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data");
	    }
        PositionDTO savedPositionDTO = positionService.createPosition(positionDTO);
        
        return new ResponseEntity<>(savedPositionDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
    public ResponseEntity<List<PositionDTO>> getAllPosition(){
    	List<PositionDTO> positions = positionService.getAllPosition();
    	return new ResponseEntity<>(positions, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable("id") Long positionId){
    	positionService.deletePosition(positionId);
    	return new ResponseEntity<>("Position successfully deleted!", HttpStatus.OK);
    }
}
