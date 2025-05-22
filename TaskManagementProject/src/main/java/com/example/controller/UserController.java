package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.UsersDto;
import com.example.response.UsersResponse;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public UsersResponse createUser(@RequestBody UsersDto dto) {
		return userService.createUser(dto);
	}

	@GetMapping("/all")
	public List<UsersResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userid}")
	public UsersResponse getUserById(@PathVariable(value = "userid") Long userid)
	{
		return userService.getUserById(userid);
	}

	@DeleteMapping("/delete/{userid}")
	public void deleteUser(@PathVariable(value = "userid") Long userid) {

		 userService.deleteUser(userid);
	}
}
