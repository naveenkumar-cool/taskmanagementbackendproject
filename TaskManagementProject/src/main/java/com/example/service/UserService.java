package com.example.service;

import java.util.List;

import com.example.dto.UsersDto;
import com.example.response.UsersResponse;

public interface UserService {

   public UsersResponse createUser(UsersDto dto);

	public List<UsersResponse> getAllUsers();
	
	public UsersResponse getUserById(Long userid);
	
	public void deleteUser(Long userid);
}
