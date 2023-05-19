package com.example.demo.entity;

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

	// コンストラクタ
	public Person() {
	}
	//登録用
	public Person(String name, String email,String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	//更新用
	public Person(Integer id, String name, String email,String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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
}
