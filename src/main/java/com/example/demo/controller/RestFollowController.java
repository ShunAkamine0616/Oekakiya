package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Follow;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FollowService;
import com.example.demo.service.ImageService;

@RestController
public class RestFollowController {
	@Autowired
	HttpSession session;
	@Autowired
	FollowService followService;
	@Autowired
	ImageService imageService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("checkFollow")
	public int checkFollow(Model model) {
		User userMe = (User) session.getAttribute("user");
		User userOther = (User) session.getAttribute("userOther");
		Follow getCheckFollow = followService.checkFollow(userMe.getId(), userOther.getId());
		if(getCheckFollow == null) {
			//まだいいねしてない
			return 0;
		} else {
			//既にいいねしている
			return 1;
		}
	}
	
	@GetMapping("insertFollow")
	public int insert(Model model) {
		User userMe = (User) session.getAttribute("user");
		User userOther = (User) session.getAttribute("userOther");
		followService.insert(userMe.getId(), userOther.getId());
		int count = followService.countFollow(userOther.getId());
		return count;
	}
	
	@GetMapping("liftFollow")
	public int lift(Model model) {
		User userMe = (User) session.getAttribute("user");
		User userOther = (User) session.getAttribute("userOther");
		followService.delete(userMe.getId(), userOther.getId());
		int count = followService.countFollow(userOther.getId());
		return count;
	}
}
