package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.LoginForm;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
@Controller

public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	@RequestMapping(value="/home", params = "login", method = RequestMethod.POST)
	public String index(@Validated @ModelAttribute("login") LoginForm loginform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "login";
		}
		User user =  userService.login(loginform.getAccountId(),loginform.getPassword());
		// ログイン失敗
		if(user == null) {
			String errMsg = "IDかパスワードが間違っています。";
			model.addAttribute("loginErrMsg", errMsg);
			return "login";
		} else {
			session.setAttribute("user",user);
			return "home";
		}
	}
}
