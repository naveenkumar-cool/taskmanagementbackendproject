package com.example.service;

import java.util.List;

import com.example.dto.TaskDto;
import com.example.response.TaskResponse;

public interface TaskService {

	public TaskResponse saveTask(Long userid,TaskDto dto);
	
	public List<TaskResponse> getAllTasks();
	
	public List<TaskResponse> getAllTasksByUserId(Long userid);
	
	public TaskResponse getTask(Long userid,Long tid);
	
	public void deleteTask(Long userid,Long tid);
}
