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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.urban.DTO.CompanyDTO;
import com.project.urban.Service.CompanyService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/companys")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	// build create Company REST API
    @PostMapping("/")
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO, BindingResult bindingResult){
    	if (bindingResult.hasErrors()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data");
	    }
        CompanyDTO savedCompanyDTO = companyService.createCompany(companyDTO);
        
        return new ResponseEntity<>(savedCompanyDTO, HttpStatus.CREATED);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<CompanyDTO>> getAllCompany(){
    	List<CompanyDTO> companys = companyService.getAllCompany();
    	return new ResponseEntity<>(companys, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId){
    	companyService.deleteCompany(companyId);
    	return new ResponseEntity<>("Company successfully deleted!", HttpStatus.OK);
    }
}
