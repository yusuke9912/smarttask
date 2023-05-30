package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Person;
import com.example.demo.entity.Tag;
import com.example.demo.entity.Task;
import com.example.demo.model.Account;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	Account account;

	@GetMapping("/tasks")
	public String tasks(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "maxCount", defaultValue = "10") Integer maxCount,
			Pageable pageable,
			Model model) {

		Sort sort1 = direction.equals("asc") ? Sort.by(Sort.Direction.ASC, sort) : Sort.by(Sort.Direction.DESC, sort);
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), maxCount, sort1);
		Person person = personRepository.findById(account.getPersonId()).get();
		Page<Task> taskPage = taskRepository.findAllByIsCompletedAndPerson(pageable1, false, person);

		model.addAttribute("keyword", keyword);
		model.addAttribute("page", taskPage);
		model.addAttribute("tasks", taskPage.getContent());
		model.addAttribute("sort", sort);
		model.addAttribute("direction", direction);
		model.addAttribute("maxCount", maxCount);

		LocalDateTime today = LocalDateTime.now();
		model.addAttribute("today", today);

		return "tasks";
	}

	@GetMapping("/tasks/completed")
	public String completedTasks(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "maxCount", defaultValue = "10") Integer maxCount,
			Pageable pageable,
			Model model) {

		Sort sort1 = direction.equals("asc") ? Sort.by(Sort.Direction.ASC, sort) : Sort.by(Sort.Direction.DESC, sort);
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), maxCount, sort1);
		Person person = personRepository.findById(account.getPersonId()).get();
		Page<Task> taskPage = taskRepository.findAllByIsCompletedAndPerson(pageable1, true, person);

		model.addAttribute("keyword", keyword);
		model.addAttribute("page", taskPage);
		model.addAttribute("tasks", taskPage.getContent());
		model.addAttribute("sort", sort);
		model.addAttribute("direction", direction);
		model.addAttribute("maxCount", maxCount);

		return "completedTasks";
	}

	@GetMapping("/tasks/add")
	public String create(Model model) {
		List<Task> taskList = taskRepository.findAll();
		List<Tag> tagList = tagRepository.findAll();

		model.addAttribute("tasks", taskList);
		model.addAttribute("tags", tagList);

		return "addTask";
	}

	@PostMapping("/tasks/add")
	public String store(
			@RequestParam(name = "personId", defaultValue = "") Integer personId,
			@RequestParam(name = "tagId", defaultValue = "") Integer tagId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "isCompleted", defaultValue = "") Boolean isCompleted,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "important", defaultValue = "") Integer important,
			@RequestParam(name = "dueDatetime", defaultValue = "") LocalDateTime dueDatetime,
			Model model) {

		Tag tag = tagRepository.findById(tagId).get();
		Person person = personRepository.findById(personId).get();

		Task task = new Task(person, tag, title, isCompleted, important, content, dueDatetime);
		taskRepository.save(task);

		if (account.getIsAdmin()) {
			return "redirect:/admin/tasks";
		} else {
			return "redirect:/tasks";
		}
	}

	@GetMapping("/tasks/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {

		Task task = taskRepository.findById(id).get();
		List<Tag> tagList = tagRepository.findAll();

		model.addAttribute("tags", tagList);
		model.addAttribute("task", task);
		return "editTask";
	}

	@PostMapping("/tasks/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "personId", defaultValue = "") Integer personId,
			@RequestParam(name = "tagId", defaultValue = "") Integer tagId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "isCompleted", defaultValue = "") Boolean isCompleted,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "important", defaultValue = "") Integer important,
			@RequestParam(name = "dueDatetime", defaultValue = "") LocalDateTime dueDatetime,
			@RequestParam(name = "createdDatetime", defaultValue = "") LocalDateTime createdDatetime,
			Model model) {

		Tag tag = tagRepository.findById(tagId).get();
		Person person = personRepository.findById(personId).get();

		Task task = new Task(id, person, tag, title, isCompleted, important, content, dueDatetime, createdDatetime);
		taskRepository.save(task);

		if (account.getIsAdmin()) {
			return "redirect:/admin/tasks";
		} else {
			return "redirect:/tasks";
		}
	}

	@PostMapping("/tasks/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "maxCount", defaultValue = "10") Integer maxCount,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			Pageable pageable,
			Model model) {

		taskRepository.deleteById(id);

		if (account.getIsAdmin()) {
			return "redirect:/admin/tasks?sort=" + sort + "&direction=" + direction + "&maxCount=" + maxCount + "&page=" + page;
		} else {
			return "redirect:/tasks?sort=" + sort + "&direction=" + direction + "&maxCount=" + maxCount + "&page=" + page;
		}
	}

	@PostMapping("/tasks/{id}/complete")
	public String complete(
			@PathVariable("id") Integer id,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "maxCount", defaultValue = "10") Integer maxCount,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			Pageable pageable,
			RedirectAttributes redirectAttributes) {

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		taskRepository.setIsCompleted(id, true);

		if (account.getIsAdmin()) {
			return "redirect:/admin/tasks?sort=" + sort + "&direction=" + direction + "&maxCount=" + maxCount + "&page=" + page;
		} else {
			return "redirect:/tasks?sort=" + sort + "&direction=" + direction + "&maxCount=" + maxCount + "&page=" + page;
		}
	}

	@PostMapping("/tasks/{id}/incomplete")
	public String incomplete(
			@PathVariable("id") Integer id,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "maxCount", defaultValue = "10") Integer maxCount,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			Pageable pageable,
			Model model) {

		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		taskRepository.setIsCompleted(id, false);

		if (account.getIsAdmin()) {
			return "redirect:/admin/tasks/completed?sort=" + sort + "&direction=" + direction + "&maxCount=" + maxCount + "&page=" + page;
		} else {
			return "redirect:/tasks/completed?sort=" + sort + "&direction=" + direction + "&maxCount=" + maxCount + "&page=" + page;
		}
	}

	@GetMapping("/tasks/status")
	public String update(Model model) {

		Person person = personRepository.findById(account.getPersonId()).get();

		List<String> list = taskRepository.getCountByTag(person);
		Map<String, String> map1 = new HashMap<>();
		for (String elem : list) {
			String[] test = elem.split(",");
			map1.put(test[0], test[1]);

		}

		List<String> list2 = taskRepository.getCountByIsCompleted(person);
		Map<String, String> map2 = new HashMap<>();
		for (String elem : list2) {
			String[] test = elem.split(",");
			map2.put(test[0], test[1]);

		}

		model.addAttribute("person", person);
		model.addAttribute("map1", map1);
		model.addAttribute("map2", map2);

		return "tasksStatus";

	}
}
