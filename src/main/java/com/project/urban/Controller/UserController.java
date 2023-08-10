package com.project.urban.Controller;

import com.project.urban.DTO.LoginDTO;
import com.project.urban.DTO.UserDTO;
import com.project.urban.Exception.ResourceNotFoundException;
import com.project.urban.Repository.UserRepository;
import com.project.urban.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
		UserDTO savedUserDTO = userService.createUser(userDTO);
		return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
	}

	// build get user by id REST API
	// http://localhost:8081/api/users/1
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {
		UserDTO userDTO = userService.getUserById(userId);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	// Build Get All Users REST API
	// http://localhost:8081/api/users
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Build Update User REST API

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId, @RequestBody UserDTO userDTO) {
		userDTO.setId(userId);
		UserDTO updatedUserDTO = userService.updateUser(userDTO);
		return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
	}

	// Build Delete User REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}

//	@PostMapping("/signin")
//	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDto) {
//		ResourceNotFoundException loginMesage = userService.loginUser(loginDto);
//		return new ResponseEntity<>(loginMesage, HttpStatus.OK);
//	}
	@PostMapping("/signin")
	public ResponseEntity<Object> loginUser(@RequestBody LoginDTO loginDto) {
		ResourceNotFoundException loginMesage = userService.loginUser(loginDto);
		return new ResponseEntity<Object>(loginMesage, HttpStatus.OK);
	}
	
}
