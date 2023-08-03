package com.project.urban.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.urban.DTO.PositionDTO;

@Service
public interface PositionService {
	
	List<PositionDTO> getAllPosition();
	
	PositionDTO createPosition (PositionDTO positionDTO);
	
	void deletePosition(Long positionId);
	
	
}
