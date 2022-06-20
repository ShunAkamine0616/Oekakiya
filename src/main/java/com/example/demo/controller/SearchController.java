package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Image;
import com.example.demo.service.FollowService;
import com.example.demo.service.ImageService;
@Controller
public class SearchController {
	
	@Autowired
	ImageService imageService;
	@Autowired
	FollowService followService;
	
	@RequestMapping({ "/", "/home" })
    public String index( Model model) {
		ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "id");
		model.addAttribute("imageList",imageList);
		System.out.println(imageList.get(0).getImagePath());
        return "home";
    }
	
	@RequestMapping("/search")
    public String search(@RequestParam("keyword") String key, @RequestParam("category") String category, @RequestParam("user") String user, @RequestParam("order") String order, Model model) {
        System.out.println(key);
        System.out.println(category);
        category = category.replace(" ,", "");
        System.out.println(category);
        System.out.println(user);
        System.out.println(order);
		return "home";
    }
}