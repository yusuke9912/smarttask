package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Person;
import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Transactional
	void deleteByPerson(Person person);

	@Transactional
	@Modifying
	@Query("update Task t set t.isCompleted = :bool where t.id = :id")
	int setIsCompleted(@Param("id") Integer id, @Param("bool") Boolean bool);
	
	List<Task> findAllByTitleContainingOrContentContaining(String keyword1,String keyword2);
	
	Page<Task> findAllByIsCompleted(Pageable pageable, Boolean isCompleted);
	
	Page<Task> findAllByIsCompletedAndPerson(Pageable pageable, Boolean isCompleted, Person person);
}
