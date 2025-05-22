package com.example.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dto.TaskDto;
import com.example.entity.Task;
import com.example.entity.Users;
import com.example.exception.TaskNotFound;
import com.example.exception.UserNotFound;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.response.TaskResponse;
import com.example.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public TaskResponse saveTask(Long userid, TaskDto dto) {
		Users users = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		
		Task task = new Task();
		task.setTaskName(dto.getTaskName());
		task.setUsers(users);
		taskRepository.save(task);
		TaskResponse taskResponse = mapper.map(task, TaskResponse.class);
		return taskResponse;
	}

	@Override
	public List<TaskResponse> getAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		List<TaskResponse> taskResponses = tasks.stream().map(task->mapper.map(task, TaskResponse.class)).collect(Collectors.toList());
		return taskResponses;
	}

	@Override
	public List<TaskResponse> getAllTasksByUserId(Long userid) {
		Users users = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		List<Task> responses = taskRepository.findAllByUsers_Id(userid);
		List<TaskResponse> taskResponses = responses.stream().map(task-> mapper.map(task, TaskResponse.class)).collect(Collectors.toList());
		return taskResponses;
	}

	@Override
	public TaskResponse getTask(Long userid, Long tid) {
		Users users = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		Task task = taskRepository.findById(tid).orElseThrow(()-> new TaskNotFound(String.format("Task Id %d not found", tid)));
		if(users.getId() != task.getUsers().getId())
		{
			throw new UserNotFound(String.format("Task Id %d is not belongs to User Id %d", tid,userid));
		}
		return mapper.map(task, TaskResponse.class);
	}

	@Override
	public void deleteTask(Long userid, Long tid) {

		Users users = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User Id %d not found", userid)));
		Task task = taskRepository.findById(tid).orElseThrow(()-> new TaskNotFound(String.format("Task Id %d not found", tid)));
		
		taskRepository.deleteById(tid);
		
	}

}
