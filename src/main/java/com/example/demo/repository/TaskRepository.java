package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	// ASC
	List<Task> findAllByOrderByCreatedDatetimeAsc();

	List<Task> findAllByOrderByTitleAsc();

	List<Task> findAllByOrderByContentAsc();

	List<Task> findAllByOrderByImportantAsc();

	List<Task> findAllByOrderByDueDatetimeAsc();

	// DESC
	List<Task> findAllByOrderByCreatedDatetimeDesc();

	List<Task> findAllByOrderByTitleDesc();

	List<Task> findAllByOrderByContentDesc();

	List<Task> findAllByOrderByImportantDesc();

	List<Task> findAllByOrderByDueDatetimeDesc();

	@Transactional
	void deleteByPersonId(Integer personId);

	@Transactional
	@Modifying
	@Query("update Task t set t.isCompleted = :bool where t.id = :id")
	//@Modifying(clearAutomatically = false)
	int setIsCompleted(@Param("id") Integer id, @Param("bool") Boolean bool);
}
