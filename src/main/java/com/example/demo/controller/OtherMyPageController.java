package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Follow;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.FollowService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;

@Controller
public class OtherMyPageController {
	@Autowired
	HttpSession session;
	
	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;
	@Autowired
	private ImageService imageService;
	
	@RequestMapping({"/other"})
	public String other(Model model) {
		User userMe = userService.findById(1);
		model.addAttribute("userMe", userMe);
		
		User userOther = userService.findById(2);
		model.addAttribute("userOther", userOther);
		
		Follow follow  = followService.countFollow(userOther.getId());
		model.addAttribute("follow", follow);
		
		ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByUserId(userOther.getId());
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
			System.out.println(imageList.get(0).getImagePath());
		}
		return "otherMyPage";
	}
}
