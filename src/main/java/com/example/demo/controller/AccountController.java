package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Person;
import com.example.demo.model.Account;
import com.example.demo.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	PersonRepository personRepository;

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		// セッション情報を全てクリアする
		session.invalidate();

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		if (email.isEmpty()) {
			model.addAttribute("message", "メールアドレスを入力してください");
			return "login";
		}

		if (password.isEmpty()) {
			model.addAttribute("message", "パスワードを入力してください");
			return "login";
		}

		if (!personRepository.existsByEmail(email)) {
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}

		Person person = personRepository.findByEmail(email).get();

		if (!person.getPassword().equals(password)) {
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}

		// セッション管理されたアカウント情報に名前をセット
		account.setName(person.getName());
		account.setPersonId(person.getId());
		account.setIsAdmin(person.getIsAdmin());

		if (account.getIsAdmin()) {
			return "redirect:/admin/tasks";
		} else {
			return "redirect:/tasks";
		}

	}

}
