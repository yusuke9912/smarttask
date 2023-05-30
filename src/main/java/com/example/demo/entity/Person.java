package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 顧客ID

	private String name; // 名前

	private String email; // メールアドレス

	private String password;

	@Column(name = "is_admin")
	private Boolean isAdmin;
	
	@Column(name = "created_datetime")
	private LocalDateTime createdDatetime;

	// コンストラクタ
	public Person() {
	}

	//登録用
	public Person(String name, String email, String password, Boolean isAdmin) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.createdDatetime = LocalDateTime.now();
	}

	//更新用
	public Person(Integer id, String name, String email, String password, Boolean isAdmin, LocalDateTime createdDatetime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.createdDatetime = createdDatetime;
	}

	// ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
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
