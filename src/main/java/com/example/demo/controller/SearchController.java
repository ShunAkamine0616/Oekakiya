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
@Controller
public class SearchController {
	
	@Autowired
	ImageService imageService;
	@Autowired
	FollowService followService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	HttpSession session;
	
	@RequestMapping({ "/", "/home" })
    public String index( Model model) {
		ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.findAll();
		session.setAttribute("category",categoryList);
		ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at");
		session.setAttribute("keywordHistory", "");
		session.setAttribute("categoryHistory", " ");
		session.setAttribute("userHistory", "all");
		session.setAttribute("orderHistory", "created_at");
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
			System.out.println(imageList.get(0).getImagePath());
		}
        return "home";
    }
	
	@RequestMapping("/search")
    public String search(@RequestParam("keyword") String key, @RequestParam("category") String category, @RequestParam("user") String user, @RequestParam("order") String order, Model model) {
		session.setAttribute("keywordHistory", key);
		session.setAttribute("categoryHistory", category);
		session.setAttribute("userHistory", user);
		session.setAttribute("orderHistory", order);
		ArrayList<Image> imageList = null;
		category = category.replace(" ,", "");
        if("all".equals(user)) {
        	System.out.println("all");
        	imageList = (ArrayList<Image>) imageService.findByKeyword(key, category, order);
        }else {
        	User userInfo = (User) session.getAttribute("user");
        	System.out.println("follow");
        	imageList = (ArrayList<Image>) imageService.findFollow(key, category, order, userInfo.getId());
        }
		model.addAttribute("imageList",imageList);
		return "home";
    }
}