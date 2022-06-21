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
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;

@Controller
public class categoryMgController{
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private ImageService imageservice;
	@Autowired
	private CategoryService categoryservice;
	
	@RequestMapping("/categoryMg")
	public String index(Model model) {
		//categoryを全権取得
		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		session.setAttribute("category",category);
		return "categoryMg";
	}
	@RequestMapping("/categoryEdit")
	public String edit(@RequestParam("id") int id,@RequestParam("1") String name,Model model) {
		//categoryを全権取得
		System.out.println(id);
		System.out.println(name);
		int update = categoryservice.update(id,name);
		System.out.println(update);
		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		session.setAttribute("category",category);
		return "categoryMg";
	}

	
}