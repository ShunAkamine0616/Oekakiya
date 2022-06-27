package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
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
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping({"/other"})
	public String other(@RequestParam("id") Integer userId, Model model) {
		User userMe = (User) session.getAttribute("user");
		model.addAttribute("user", userMe);
		
		User userOther = userService.findById(userId);
		session.setAttribute("userOther", userOther);
		
		Integer follow  = followService.countFollow(userOther.getId());
		
		model.addAttribute("followCnt", follow);
		
		
		List<Image> imageList = (List<Image>) imageService.findByUserId(userOther.getId());
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
			System.out.println(imageList.get(0).getImagePath());
		}
		return "otherMyPage";
	}
	
	@RequestMapping({"/deleteAccount"})
	public String deleteAccount(@RequestParam("otherId") Integer otherId, Model model) {
		System.out.println(otherId);
		if(userService.delete(otherId) == 0) {
			return "editMyPage";
		}
		User userInfo = (User) session.getAttribute("user");
		Integer userId = null;
		if(userInfo != null) {
			userId = userInfo.getId();
		}
		ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.findAll();
		session.setAttribute("category",categoryList);
		ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at DESC", userId);
		session.setAttribute("keywordHistory", "");
		session.setAttribute("categoryHistory", " ");
		session.setAttribute("userHistory", "all");
		session.setAttribute("orderHistory", "created_at");
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
		}
		model.addAttribute("delete","1");
		return "home";
	}
}
