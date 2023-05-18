package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;

@Controller
public class PersonController {
	@Autowired
	PersonRepository personRepository;

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

}
