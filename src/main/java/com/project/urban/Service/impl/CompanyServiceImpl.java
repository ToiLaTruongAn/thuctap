package com.project.urban.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.urban.DTO.CompanyDTO;
import com.project.urban.Entity.Company;
import com.project.urban.Repository.CompanyRepository;
import com.project.urban.Service.CompanyService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository Comrepo;

	@Override
	public CompanyDTO createCompany(CompanyDTO companyDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Company company = modelMapper.map(companyDTO, Company.class);
		Company savedCompany = Comrepo.save(company);
		CompanyDTO savedCompanyDTO = modelMapper.map(savedCompany, CompanyDTO.class);
		return savedCompanyDTO;
	}

	@Override
	public List<CompanyDTO> getAllCompany() {
		List<CompanyDTO> allCompany = new ArrayList<>();
		List<Company> companys = Comrepo.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (Company company : companys) {
			CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);
			allCompany.add(companyDTO);
		}
		return allCompany;
	}

	@Override
	public void deleteCompany(Long companyId) {
		Comrepo.deleteById(companyId);

	}
}
