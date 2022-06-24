package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;

@RestController
public class RestRoleController {
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	ImageService imageService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("checkRole")
	public int checkRole(Model model) {
		User userOther = (User) session.getAttribute("userOther");
		if(userOther.getRole() == 1) {
			//管理者
			return 0;
		} else {
			//一般ユーザー
			return 1;
		}
	}
	
	@GetMapping("assignAdmin")
	public int assign(Model model) {
		User userOther = (User) session.getAttribute("userOther");
		int assign = userService.roleAssignment(userOther, true);
		
		return 0;
	}
	
	@GetMapping("deprivationAdmin")
	public int deprivation(Model model) {
		User userOther = (User) session.getAttribute("userOther");
		userService.roleAssignment(userOther, false);
		return 0;
	}
}
