package com.example.demo.controller.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			Pageable pageable,
			Model model) {

		Sort sort1 = direction.equals("asc") ? Sort.by(Sort.Direction.ASC, sort) : Sort.by(Sort.Direction.DESC, sort);
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), 10, sort1);
		Page<Person> personPage = personRepository.findAll(pageable1);

		model.addAttribute("page", personPage);
		model.addAttribute("persons", personPage.getContent());
		model.addAttribute("sort", sort);
		model.addAttribute("direction", direction);

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
			@RequestParam(name = "createdDatetime", defaultValue = "") LocalDateTime createdDatetime,
			Pageable pageable,
			Model model) {

		Person person = new Person(id, name, email, password, isAdmin, createdDatetime);
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
