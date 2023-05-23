package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "person_id")
	private Integer personId;
	
	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tag tag;

	private String title;

	@Column(name = "is_completed")
	private Boolean isCompleted;

	private Integer important;

	private String content;

	@Column(name = "due_datetime")
	private LocalDateTime dueDatetime;

	@Column(name = "created_datetime")
	private LocalDateTime createdDatetime;

	public Task() {
	}

	// 更新用
	public Task(Integer id, Integer personId, Tag tag, String title, Boolean isCompleted, Integer important,
			String content,
			LocalDateTime dueDatetime, LocalDateTime createdDatetime) {
		this.id = id;
		this.personId = personId;
		this.tag = tag;
		this.title = title;
		this.isCompleted = isCompleted;
		this.important = important;
		this.content = content;
		this.dueDatetime = dueDatetime;
		this.createdDatetime = createdDatetime;
	}

	// 新規作成用
	public Task(Integer personId, Tag tag, String title, Boolean isCompleted, Integer important, String content,
			LocalDateTime dueDatetime) {
		this.personId = personId;
		this.tag = tag;
		this.title = title;
		this.isCompleted = isCompleted;
		this.important = important;
		this.content = content;
		this.dueDatetime = dueDatetime;
		this.createdDatetime = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public Integer getTagId() {
		return tag.getId();
	}
	
	public String getTagName() {
		return tag.getName();
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

	public LocalDateTime getDueDatetime() {
		return dueDatetime;
	}

	public String getDueDatetimeFormatted() {
		DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

		//指定の書式に日付データを渡す
		return datetimeformatter.format(dueDatetime);

	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public String getCreatedDatetimeFormatted() {
		DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

		//指定の書式に日付データを渡す
		return datetimeformatter.format(createdDatetime);

	}

}
