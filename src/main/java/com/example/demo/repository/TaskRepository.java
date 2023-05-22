package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	List<Task> findAllByOrderByCreatedDatetime();

	@Transactional
	void deleteByPersonId(Integer personId);
}
