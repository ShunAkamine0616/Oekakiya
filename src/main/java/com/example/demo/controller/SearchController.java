package com.example.demo.controller;

import java.util.ArrayList;

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
public class SearchController {
	
	@Autowired
	ImageService imageService;
	@Autowired
	FollowService followService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	
	@RequestMapping({ "/", "/home" })
    public String index( Model model) {
		ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.findAll();
		session.setAttribute("category",categoryList);
		ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at DESC",2);
		session.setAttribute("keywordHistory", "");
		session.setAttribute("categoryHistory", " ");
		session.setAttribute("userHistory", "all");
		session.setAttribute("orderHistory", "created_at");
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
		}
		//model.addAttribute("delete",1);
        return "home";
    }
	
	@RequestMapping("/search")
    public String search(@RequestParam("keyword") String key, @RequestParam("category") String category, @RequestParam("user") String user, @RequestParam("order") String order, Model model) {
		session.setAttribute("keywordHistory", key);
		session.setAttribute("categoryHistory", category);
		session.setAttribute("userHistory", user);
		session.setAttribute("orderHistory", order);
		User userInfo = (User) session.getAttribute("user");
		Integer userId = null;
		if(userInfo != null) {
			userId = userInfo.getId();
		}
		ArrayList<Image> imageList = null;
		category = category.replace(" ,", "");
        if("all".equals(user)) {
        	imageList = (ArrayList<Image>) imageService.findByKeyword(key, category, order, userId);
        	System.out.println("user");
        }else {
        	imageList = (ArrayList<Image>) imageService.findFollow(key, category, order, userId);
        	System.out.println("follow");
        }
        System.out.println(imageList);
        model.addAttribute("imageList",imageList);
		return "home";
    }
	
	@RequestMapping("/userSearch")
    public String userSearch( Model model) {
		return "homeUserSearch";
	}
	
	@RequestMapping("/searchUser")
    public String searchUser(@RequestParam("keyword") String key, @RequestParam("user") String user, Model model) {
		session.setAttribute("keywordHistory", key);
		session.setAttribute("userHistory", user);
		ArrayList<User> userList = null;
		if("all".equals(user)) {
			userList = (ArrayList<User>) userService.findByKeyword(key);
		}else {
			User userInfo = (User) session.getAttribute("user");
			userList = (ArrayList<User>) userService.findFollow(key,userInfo.getId());
		}
		if(userList != null) {
			model.addAttribute("userList",userList);
		}
		return "homeUserSearch";
	}
}