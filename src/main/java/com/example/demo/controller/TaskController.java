package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {
	@Autowired
	TaskRepository taskRepository;

	@GetMapping("/tasks")
	public String tasks(Model model) {

		List<Task> taskList = taskRepository.findAll();

		model.addAttribute("tasks", taskList);

		return "tasks";
	}

	@GetMapping("/tasks/add")
	public String create(Model model) {
		List<Task> taskList = taskRepository.findAll();

		model.addAttribute("tasks", taskList);

		return "addTask";
	}

	@PostMapping("/tasks/add")
	public String store(
			@RequestParam(name = "personId", defaultValue = "") Integer personId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "isCompleted", defaultValue = "") Boolean isCompleted,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "important", defaultValue = "") Integer important,
			@RequestParam(name = "dueDate", defaultValue = "") String dueDate,
			@RequestParam(name = "createdDate", defaultValue = "") String createdDate,
			Model model) {

		Task task = new Task(personId, title, isCompleted, important, content, dueDate, createdDate);
		taskRepository.save(task);

		return "redirect:/tasks";
	}

	@GetMapping("/tasks/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {

		Task task = taskRepository.findById(id).get();
		model.addAttribute("task", task);
		return "editTask";
	}

	@PostMapping("/tasks/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "personId", defaultValue = "") Integer personId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "isCompleted", defaultValue = "") Boolean isCompleted,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "important", defaultValue = "") Integer important,
			@RequestParam(name = "dueDate", defaultValue = "") String dueDate,
			@RequestParam(name = "createdDate", defaultValue = "") String createdDate,
			Model model) {

		Task task = new Task(id, personId, title, isCompleted, important, content, dueDate, createdDate);
		taskRepository.save(task);

		return "redirect:/tasks";
	}

	@PostMapping("/tasks/{id}/delete")
	public String delete(@PathVariable("id") Integer id, Model model) {

		taskRepository.deleteById(id);

		return "redirect:/tasks";
	}
	
	@PostMapping("/tasks/{id}/complete")
	public String complete(@PathVariable("id") Integer id, Model model) {

		Task task1 = taskRepository.findById(id).get();
		Task task2 = new Task(task1.getId(), task1.getPersonId(), task1.getTitle(), true, task1.getImportant(), task1.getContent(), task1.getDueDate(), task1.getCreatedDate());
		taskRepository.save(task2);

		return "redirect:/tasks";
	}
}