package com.example.demo.controller;

import java.time.LocalDateTime;
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
	public String tasks(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		List<Task> taskList = null;

		if (sort.isEmpty()) {
			taskList = taskRepository.findAllByOrderByCreatedDatetimeAsc();
		} else if (sort.equals("titleAsc")) {
			taskList = taskRepository.findAllByOrderByTitleAsc();
		} else if (sort.equals("contentAsc")) {
			taskList = taskRepository.findAllByOrderByContentAsc();
		} else if (sort.equals("importantAsc")) {
			taskList = taskRepository.findAllByOrderByImportantAsc();
		} else if (sort.equals("dueDatetimeAsc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeAsc();
		} else if (sort.equals("titleDesc")) {
			taskList = taskRepository.findAllByOrderByTitleDesc();
		} else if (sort.equals("contentDesc")) {
			taskList = taskRepository.findAllByOrderByContentDesc();
		} else if (sort.equals("importantDesc")) {
			taskList = taskRepository.findAllByOrderByImportantDesc();
		} else if (sort.equals("dueDatetimeDesc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeDesc();
		}

		model.addAttribute("tasks", taskList);
		model.addAttribute("sort", sort);

		return "tasks";
	}

	@GetMapping("/tasks/completed")
	public String completedTasks(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		List<Task> taskList = null;

		if (sort.isEmpty()) {
			taskList = taskRepository.findAllByOrderByCreatedDatetimeAsc();
		} else if (sort.equals("titleAsc")) {
			taskList = taskRepository.findAllByOrderByTitleAsc();
		} else if (sort.equals("contentAsc")) {
			taskList = taskRepository.findAllByOrderByContentAsc();
		} else if (sort.equals("importantAsc")) {
			taskList = taskRepository.findAllByOrderByImportantAsc();
		} else if (sort.equals("dueDatetimeAsc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeAsc();
		} else if (sort.equals("titleDesc")) {
			taskList = taskRepository.findAllByOrderByTitleDesc();
		} else if (sort.equals("contentDesc")) {
			taskList = taskRepository.findAllByOrderByContentDesc();
		} else if (sort.equals("importantDesc")) {
			taskList = taskRepository.findAllByOrderByImportantDesc();
		} else if (sort.equals("dueDatetimeDesc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeDesc();
		}

		model.addAttribute("tasks", taskList);
		model.addAttribute("sort", sort);

		return "completedTasks";
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
			@RequestParam(name = "dueDatetime", defaultValue = "") LocalDateTime dueDatetime,
			Model model) {

		Task task = new Task(personId, title, isCompleted, important, content, dueDatetime);
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
			@RequestParam(name = "dueDatetime", defaultValue = "") LocalDateTime dueDatetime,
			@RequestParam(name = "createdDatetime", defaultValue = "") LocalDateTime createdDatetime,
			Model model) {

		Task task = new Task(id, personId, title, isCompleted, important, content, dueDatetime, createdDatetime);
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
		taskRepository.setIsCompleted(id, true);

		return "redirect:/tasks";
	}

	@PostMapping("/tasks/{id}/incomplete")
	public String incomplete(@PathVariable("id") Integer id, Model model) {

		taskRepository.setIsCompleted(id, false);

		return "redirect:/tasks/completed";
	}
}
