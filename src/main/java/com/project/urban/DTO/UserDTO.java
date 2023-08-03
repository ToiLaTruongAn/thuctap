package com.project.urban.DTO;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String MaNV;
	private String username;
	private String password;
	private String email;
	private String name;
	private Long companyId;
	private Long positionId;
	private String role;
}
