package com.project.urban.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.urban.DTO.CompanyDTO;

@Service
public interface CompanyService {
	
	List<CompanyDTO> getAllCompany();
	 
	CompanyDTO createCompany (CompanyDTO companyDTO);
	
	void deleteCompany(Long companyId);
	
}
