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

import com.example.dto.TaskDto;
import com.example.response.TaskResponse;
import com.example.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/{userid}/save")
	public TaskResponse saveTask(@PathVariable(name = "userid")Long userid,@RequestBody TaskDto dto)
	{
		return taskService.saveTask(userid, dto);
	}
	
	@GetMapping("all")
	public List<TaskResponse> getAllTasks()
	{
		return taskService.getAllTasks();
	}
	@GetMapping("/{userid}/tasks")
	public List<TaskResponse> getAllTasksByUserId(@PathVariable(value = "userid") Long userid)
	{
		return taskService.getAllTasksByUserId(userid);
	}
	
	@GetMapping("/{userid}/tasks/{tid}")
	public TaskResponse getTask(@PathVariable(name = "userid")long userid,@PathVariable(name = "tid")long tid)
	{
		return taskService.getTask(userid, tid);
	}
	@DeleteMapping("/delete/{userid}/tasks/{tid}")
	public void deleteTask(@PathVariable(name = "userid")long userid,@PathVariable(name = "tid")long tid)
	{
		 taskService.deleteTask(userid, tid);
	}
}
