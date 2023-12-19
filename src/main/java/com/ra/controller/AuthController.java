package com.ra.controller;

import com.ra.model.Roles;
import com.ra.model.Users;
import com.ra.repository.IRoleRepository;
import com.ra.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IRoleRepository roleRepository;
	@Autowired
	private IUserRepository userRepository;
	
	@GetMapping("/login")
	public String handleLogin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showFormRegister(Model model) {
		model.addAttribute("userRegister", new Users());
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegister(@ModelAttribute("userRegister") Users users) {
		
		Roles role = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("role not found"));
		Set<Roles> roles = new HashSet<>();
		roles.add(role);
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		users.setStatus(true);
		users.setRoles(roles);
		System.out.println(users);
		userRepository.save(users);
		return "login";
	}
	
}
