package com.example.demo.controller;

import java.util.ArrayList;

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
	@GetMapping({ "/login", "/logout" })
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

		// 「/items」へのリダイレクト
		return "redirect:/tasks";
	}

	@GetMapping("/account")
	public String create() {
		return "accountForm";
	}

	@PostMapping("/account")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		ArrayList<String> errors = new ArrayList<String>();

		if (name.isEmpty()) {
			errors.add("名前は必須です");
		}

		if (address.isEmpty()) {
			errors.add("住所は必須です");
		}

		if (tel.isEmpty()) {
			errors.add("電話番号は必須です");
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
		model.addAttribute("address", address);
		model.addAttribute("tel", tel);
		model.addAttribute("email", email);
		model.addAttribute("errors", errors);

		if (errors.isEmpty()) {
			Person person = new Person(name, email, password);
			personRepository.save(person);
			return "redirect:/login";
		} else {
			return "accountForm";
		}

	}

}
