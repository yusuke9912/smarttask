package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Account {
	private String name;
	private Integer personId;

	public Account() {
	}

	public Account(Integer userId, String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Integer getPersonId() {
		return personId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
}
