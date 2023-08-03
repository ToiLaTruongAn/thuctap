package com.project.urban.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.urban.DTO.LoginDTO;
import com.project.urban.DTO.UserDTO;
import com.project.urban.Entity.Company;
import com.project.urban.Entity.Position;
import com.project.urban.Entity.User;
import com.project.urban.Exception.Constant;
import com.project.urban.Exception.ErrorConstant;
import com.project.urban.Exception.ResourceNotFoundException;
import com.project.urban.Exception.ResponseCode;
import com.project.urban.Repository.CompanyRepository;
import com.project.urban.Repository.PositionRepository;
import com.project.urban.Repository.UserRepository;
import com.project.urban.Service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CompanyRepository comRepo;
	@Autowired
	private PositionRepository PoRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		Company company = comRepo.findById(userDTO.getCompanyId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "company not found"));
		Position position = PoRepo.findById(userDTO.getPositionId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "position not found"));

		// Mã hóa password
		String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDTO, User.class);
		user.setCompany(company);
		user.setPosition(position);
		user.setRole("Role_User");
		user.setPassword(hashedPassword); // Lưu password đã mã hóa
		User savedUser = userRepository.save(user);

		UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);
		savedUserDTO.setCompanyId(savedUser.getCompany().getId());
		savedUserDTO.setPositionId(savedUser.getPosition().getId());

		return savedUserDTO;
	}

	@Override
	public UserDTO getUserById(Long userId) {
		ModelMapper modelMapper = new ModelMapper();

		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> allUsers = new ArrayList<>();
		List<User> users = userRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (User user : users) {
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			allUsers.add(userDTO);
		}
		return allUsers;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		// Lấy đối tượng User từ cơ sở dữ liệu
		User existingUser = userRepository.findById(userDTO.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

		// Ánh xạ các trường của đối tượng UserDTO vào đối tượng User
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(userDTO, existingUser);

		// Lưu đối tượng User đã cập nhật vào cơ sở dữ liệu
		User updatedUser = userRepository.save(existingUser);

		// Ánh xạ đối tượng User đã cập nhật thành đối tượng UserDTO và trả về
		UserDTO updatedUserDTO = modelMapper.map(updatedUser, UserDTO.class);
		return updatedUserDTO;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		userRepository.deleteById(user.getId());
	}

	@Override
	public ResourceNotFoundException loginUser(LoginDTO loginDTO) {
		Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
		if (user.isEmpty()) {
			return new ResourceNotFoundException(ResponseCode.CODE_404, ErrorConstant.NOT_FOUND, null);
		} else {
			User foundUser = user.get();
			if (foundUser.getPassword() != null) {
				boolean isPwdRight = passwordEncoder.matches(loginDTO.getPassword(), foundUser.getPassword());
				if (isPwdRight) {
					return new ResourceNotFoundException(ResponseCode.CODE_200, Constant.LOGIN_SUCCESS, user);
				}
			}
		}
		return new ResourceNotFoundException(ResponseCode.CODE_500, ErrorConstant.INTERNAL_SERVER_ERROR, null);
	}
}
