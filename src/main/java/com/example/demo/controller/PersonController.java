package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Person;
import com.example.demo.entity.Task;
import com.example.demo.model.Account;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PersonController {
	@Autowired
	HttpSession session;

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	Account account;
	
	@GetMapping("/persons")
	public String persons(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		
		List<Person> personList = personRepository.findAll();
		

		model.addAttribute("persons", personList);
		model.addAttribute("sort", sort);

		return "admin/persons";
	}

	@GetMapping("/persons/add")
	public String create() {
		return "addPerson";
	}

	@PostMapping("/persons/add")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		ArrayList<String> errors = new ArrayList<String>();

		if (name.isEmpty()) {
			errors.add("名前は必須です");
		}

		if (email.isEmpty()) {
			errors.add("メールアドレスは必須です");
		}

		if (personRepository.existsByEmail(email)) {
			errors.add("登録済みのメールアドレスです");
		}

		if (password.isEmpty()) {
			errors.add("パスワードは必須です");
		}

		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("errors", errors);

		if (errors.isEmpty()) {
			Person person = new Person(name, email, password);
			personRepository.save(person);
			return "redirect:/login";
		} else {
			return "addPerson";
		}

	}

	@GetMapping("/persons/edit")
	public String edit(Model model) {

		Person person = personRepository.findById(account.getPersonId()).get();

		model.addAttribute("name", person.getName());
		model.addAttribute("email", person.getEmail());
		model.addAttribute("password", person.getPassword());

		return "editPerson";
	}

	@PostMapping("/persons/edit")
	public String update(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		ArrayList<String> errors = new ArrayList<String>();

		if (name.isEmpty()) {
			errors.add("名前は必須です");
		}

		if (email.isEmpty()) {
			errors.add("メールアドレスは必須です");
		}

		if (password.isEmpty()) {
			errors.add("パスワードは必須です");
		}

		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("email", password);
		model.addAttribute("errors", errors);

		if (errors.isEmpty()) {
			Person person = new Person(account.getPersonId(), name, email, password);
			personRepository.save(person);
			return "redirect:/tasks";
		} else {
			return "editPerson";
		}

	}

	@PostMapping("/persons/delete")
	public String delete() {
		//ユーザーに紐づいたタスクを削除
		Person person = personRepository.findById(account.getPersonId()).get();
		taskRepository.deleteByPerson(person);
		
		//ユーザー自体を削除
		personRepository.deleteById(account.getPersonId());
		
		return "redirect:/login";

	}

}
