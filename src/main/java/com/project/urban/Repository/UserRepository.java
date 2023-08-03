package com.project.urban.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.urban.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findOneByEmailAndPassword(String email, String password);
	
	Boolean existsByEmail(String email);
	
	

}
