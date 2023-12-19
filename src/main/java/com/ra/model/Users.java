package com.ra.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullName;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private Boolean status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "role_detail",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles;
	
	
}
