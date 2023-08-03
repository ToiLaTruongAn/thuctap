package com.project.urban.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.urban.DTO.PositionDTO;
import com.project.urban.Entity.Position;
import com.project.urban.Repository.PositionRepository;
import com.project.urban.Service.PositionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService{

	@Autowired 
	private PositionRepository PosiRepo;
	
	@Override
	public PositionDTO createPosition(PositionDTO positionDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Position position = modelMapper.map(positionDTO, Position.class);
		Position savedPosition = PosiRepo.save(position);
		PositionDTO savedPositionDTO = modelMapper.map(savedPosition, PositionDTO.class);
		return savedPositionDTO;
	}

	@Override
	public List<PositionDTO> getAllPosition() {
		List<PositionDTO> allPosition = new ArrayList<>();
		List<Position> positions = PosiRepo.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (Position position : positions) {
			PositionDTO positionDTO = modelMapper.map(position, PositionDTO.class);
			allPosition.add(positionDTO);
		}
		return allPosition;
	}

	@Override
	public void deletePosition(Long positionId) {
		PosiRepo.deleteById(positionId);

	}
}
