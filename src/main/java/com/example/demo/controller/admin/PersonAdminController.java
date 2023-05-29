package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Person;
import com.example.demo.model.Account;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class PersonAdminController {
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	Account account;

	@GetMapping("/admin/persons")
	public String persons(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		List<Person> personList = personRepository.findAllByOrderById();

		model.addAttribute("persons", personList);
		model.addAttribute("sort", sort);

		return "admin/persons";
	}

	@GetMapping("/admin/persons/add")
	public String create() {
		return "admin/addPerson";
	}

	@PostMapping("/admin/persons/add")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "isAdmin", defaultValue = "") Boolean isAdmin,
			Model model) {

		ArrayList<String> errors = new ArrayList<String>();

		Person person = new Person(name, email, password, isAdmin);
		personRepository.save(person);
		return "redirect:/admin/persons";

	}

	@GetMapping("/admin/persons/{id}/edit")
	public String tasks(@PathVariable("id") Integer id, Model model) {

		Person person = personRepository.findById(id).get();


		model.addAttribute("person", person);


		return "admin/editPerson";
	}

	@PostMapping("/admin/persons/{id}/edit")
	public String completedTasks(
			@PathVariable("id") Integer id,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "isAdmin", defaultValue = "") Boolean isAdmin,
			Pageable pageable,
			Model model) {

		Person person = new Person(id, name, email, password, isAdmin);
		personRepository.save(person);

		return "redirect:/admin/persons";
	}

	@PostMapping("/admin/persons/{id}/delete")
	public String completedTasks(@PathVariable("id") Integer id, Model model) {

		//ユーザーに紐づいたタスクを削除
		Person person = personRepository.findById(id).get();
		taskRepository.deleteByPerson(person);

		//ユーザー自体を削除
		personRepository.deleteById(id);

		return "redirect:/admin/persons";
	}
}
