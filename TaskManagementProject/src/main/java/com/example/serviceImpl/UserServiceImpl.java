package com.example.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dto.UsersDto;
import com.example.entity.Users;
import com.example.exception.UserNotFound;
import com.example.repository.UserRepository;
import com.example.response.UsersResponse;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UsersResponse createUser(UsersDto dto) {
		Users users = new Users();
		users.setName(dto.getName());
		users.setEmail(dto.getEmail());
		users.setPassword(dto.getPassword());
		userRepository.save(users);
		UsersResponse usersResponse = mapper.map(users, UsersResponse.class);
		return usersResponse;
	}

	@Override
	public List<UsersResponse> getAllUsers() {
		List<Users> users = userRepository.findAll();
		//users.forEach(user -> System.out.println(user));
		List<UsersResponse> usersResponses = users.stream().map(user -> mapper.map(user, UsersResponse.class)).collect(Collectors.toList());
		return usersResponses;
	}

	@Override
	public UsersResponse getUserById(Long userid) {
		Users users = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		UsersResponse usersResponse = mapper.map(users, UsersResponse.class);
		return usersResponse;
	}

	@Override
	public void deleteUser(Long userid) {
		Users users = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		userRepository.deleteById(userid);
	}

}
