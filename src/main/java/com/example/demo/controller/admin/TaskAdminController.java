package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "maxCount", defaultValue = "5") Integer maxCount,
			Pageable pageable,
			Model model) {

		Sort sort1 = direction.equals("asc") ? Sort.by(Sort.Direction.ASC, sort) : Sort.by(Sort.Direction.DESC, sort);
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), maxCount, sort1);
		Page<Task> taskPage = taskRepository.findAllByIsCompleted(pageable1, false);

		model.addAttribute("keyword", keyword);
		model.addAttribute("page", taskPage);
		model.addAttribute("tasks", taskPage.getContent());
		model.addAttribute("sort", sort);
		model.addAttribute("direction", direction);
		model.addAttribute("maxCount", maxCount);

		return "admin/tasks";
	}

	@GetMapping("/admin/tasks/completed")
	public String completedTasks(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "sort", defaultValue = "id") String sort,
			@RequestParam(name = "direction", defaultValue = "asc") String direction,
			@RequestParam(name = "maxCount", defaultValue = "5") Integer maxCount,
			Pageable pageable,
			Model model) {
		
		Sort sort1 = direction.equals("asc") ? Sort.by(Sort.Direction.ASC, sort) : Sort.by(Sort.Direction.DESC, sort);
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), maxCount, sort1);
		Page<Task> taskPage = taskRepository.findAllByIsCompleted(pageable1, true);

		model.addAttribute("keyword", keyword);
		model.addAttribute("page", taskPage);
		model.addAttribute("tasks", taskPage.getContent());
		model.addAttribute("sort", sort);
		model.addAttribute("direction", direction);
		model.addAttribute("maxCount", maxCount);

		return "admin/completedTasks";
	}
}
