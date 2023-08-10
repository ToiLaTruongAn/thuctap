package com.project.urban.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "MaNV" }),
		@UniqueConstraint(columnNames = { "email" }) })
@JsonIgnoreProperties
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY phù hợp với các cơ sở dữ liệu hỗ trợ tự động tăng
														// giá trị ID như MySQL hoặc PostgreSQL.
	private Long id;

	@Column(name = "MaNV", length = 12, nullable = false, unique = true)
	private String MaNV;

	@Column(name = "password", length = 250, nullable = false)
	@NotBlank(message = "Password is required")
	private String password;

	@Column(name = "email", length = 50)
	@Size(max = 50, message = "Email must be less than 50 characters")
	private String email;

	@Column(name = "name", length = 50, nullable = false)
	@Size(max = 50, message = "Your name must be less than 50 characters")
	@NotBlank(message = "Your name is required")
	private String name;

	private String role;
	// relationship
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "Company_id", referencedColumnName = "id")
	private Company company;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "Position_id", referencedColumnName = "id")
	private Position position;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Event> Events = new ArrayList<>();
}
