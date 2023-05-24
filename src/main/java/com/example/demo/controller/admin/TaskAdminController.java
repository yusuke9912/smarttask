package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.model.Account;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskAdminController {
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	Account account;

	@GetMapping("/admin/tasks")
	public String tasks(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		List<Task> taskList = null;

		if (sort.isEmpty() || sort.equals("createdDatetimeAsc")) {
			taskList = taskRepository.findAllByOrderByCreatedDatetimeAsc();
		} else if (sort.equals("titleAsc")) {
			taskList = taskRepository.findAllByOrderByTitleAsc();
		} else if (sort.equals("contentAsc")) {
			taskList = taskRepository.findAllByOrderByContentAsc();
		} else if (sort.equals("importantAsc")) {
			taskList = taskRepository.findAllByOrderByImportantAsc();
		} else if (sort.equals("dueDatetimeAsc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeAsc();
		} else if (sort.equals("tagAsc")) {
			taskList = taskRepository.findAllByOrderByTagAsc();
		} else if (sort.equals("personAsc")) {
			taskList = taskRepository.findAllByOrderByPersonAsc();
		} else if (sort.equals("createdDatetimeDesc")) {
			taskList = taskRepository.findAllByOrderByCreatedDatetimeDesc();
		} else if (sort.equals("titleDesc")) {
			taskList = taskRepository.findAllByOrderByTitleDesc();
		} else if (sort.equals("contentDesc")) {
			taskList = taskRepository.findAllByOrderByContentDesc();
		} else if (sort.equals("importantDesc")) {
			taskList = taskRepository.findAllByOrderByImportantDesc();
		} else if (sort.equals("dueDatetimeDesc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeDesc();
		} else if (sort.equals("tagDesc")) {
			taskList = taskRepository.findAllByOrderByTagDesc();
		} else if (sort.equals("personDesc")) {
			taskList = taskRepository.findAllByOrderByPersonDesc();
		}

		model.addAttribute("tasks", taskList);
		model.addAttribute("sort", sort);

		return "admin/tasks";
	}

	@GetMapping("/admin/tasks/completed")
	public String completedTasks(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			Model model) {

		List<Task> taskList = null;

		if (sort.isEmpty() || sort.equals("createdDatetimeAsc")) {
			taskList = taskRepository.findAllByOrderByCreatedDatetimeAsc();
		} else if (sort.equals("titleAsc")) {
			taskList = taskRepository.findAllByOrderByTitleAsc();
		} else if (sort.equals("contentAsc")) {
			taskList = taskRepository.findAllByOrderByContentAsc();
		} else if (sort.equals("importantAsc")) {
			taskList = taskRepository.findAllByOrderByImportantAsc();
		} else if (sort.equals("dueDatetimeAsc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeAsc();
		} else if (sort.equals("tagAsc")) {
			taskList = taskRepository.findAllByOrderByTagAsc();
		} else if (sort.equals("personAsc")) {
			taskList = taskRepository.findAllByOrderByPersonAsc();
		} else if (sort.equals("createdDatetimeDesc")) {
			taskList = taskRepository.findAllByOrderByCreatedDatetimeDesc();
		} else if (sort.equals("titleDesc")) {
			taskList = taskRepository.findAllByOrderByTitleDesc();
		} else if (sort.equals("contentDesc")) {
			taskList = taskRepository.findAllByOrderByContentDesc();
		} else if (sort.equals("importantDesc")) {
			taskList = taskRepository.findAllByOrderByImportantDesc();
		} else if (sort.equals("dueDatetimeDesc")) {
			taskList = taskRepository.findAllByOrderByDueDatetimeDesc();
		} else if (sort.equals("tagDesc")) {
			taskList = taskRepository.findAllByOrderByTagDesc();
		} else if (sort.equals("personDesc")) {
			taskList = taskRepository.findAllByOrderByPersonDesc();
		}

		model.addAttribute("tasks", taskList);
		model.addAttribute("sort", sort);

		return "admin/completedTasks";
	}
}
