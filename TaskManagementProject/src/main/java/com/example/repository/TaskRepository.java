package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Task;
import com.example.response.TaskResponse;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByUsers_Id(Long userId);


}
