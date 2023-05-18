package com.example.demo.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "person_id")
	private Integer personId;

	private String title;

	@Column(name = "is_completed")
	private Boolean isCompleted;

	private Integer important;

	private String content;

	@Column(name = "due_date")
	private LocalDate dueDate;

	@Column(name = "created_date")
	private LocalDate createdDate;

	public Task() {
	}

	// 更新用
	public Task(Integer id, Integer personId, String title, Boolean isCompleted, Integer important, String content,
			String dueDate, String createdDate) {
		this.id = id;
		this.personId = personId;
		this.title = title;
		this.isCompleted = isCompleted;
		this.important = important;
		this.content = content;
		this.dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		this.createdDate = LocalDate.parse(createdDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}

	// 新規作成用
	public Task(Integer personId, String title, Boolean isCompleted, Integer important, String content, String dueDate,
			String createdDate) {
		this.personId = personId;
		this.title = title;
		this.isCompleted = isCompleted;
		this.important = important;
		this.content = content;
		this.dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		this.createdDate = LocalDate.parse(createdDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}

	public Integer getId() {
		return id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public Integer getImportant() {
		return important;
	}

	public String getContent() {
		return content;
	}

	public String getDueDate() {
		return dueDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}

	public String getCreatedDate() {
		return createdDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}
}
