package com.project.urban.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.urban.Entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
