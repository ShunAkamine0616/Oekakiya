package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;

@Controller
public class CategoryMgController {

	@Autowired
	HttpSession session;
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private CategoryService categoryservice;
	@Autowired
	ImageService imageService;

	@RequestMapping("/categoryMg")
	public String index(Model model) {
		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at DESC", null);
			if(imageList != null) {
				model.addAttribute("imageList",imageList);
			}
			return"home";
		}
		int role = user.getRole();
		if(role==1) {
			session.setAttribute("category", category);
			return "categoryMg";
		}else {
			ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at DESC", null);
			if(imageList != null) {
				model.addAttribute("imageList",imageList);
			}
			return "home";
		}

	}

	@RequestMapping(value = "/categoryEdit", params = "editId", method = RequestMethod.POST)
	public String edit(@RequestParam("editId") Integer id,  Model model) {
		String name = req.getParameter(id.toString());
		if (name.equals("")) {
			model.addAttribute("msg", "カテゴリ名は必須入力項目です。");
			return "categoryMg";
		} else {
	        try {
	        	categoryservice.update(id, name);
	        } catch (Exception e) {
				model.addAttribute("msg", "255文字以内で入力してください");
				return "categoryMg";
	        }
			List<Category> category = new ArrayList<>();
			category = categoryservice.findAll();
			session.setAttribute("category", category);
			return "categoryMg";
		}
	}

	@RequestMapping(value = "/categoryEdit", params = "deleteId", method = RequestMethod.POST)
	public String delete(@RequestParam("deleteId") Integer[] id, Model model) {
		for (Integer i : id) {
			int delete = categoryservice.delete(i);
			System.out.println(delete);
			model.addAttribute("delete", delete);
		}

		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		session.setAttribute("category", category);
		return "categoryMg";
	}

	@RequestMapping(value = "/categoryInsert", method = RequestMethod.POST)
	public String insert(@RequestParam("name") String name, Model model) {
		if (name.equals("")) {
			model.addAttribute("msg", "カテゴリ名は必須入力項目です。");
			return "categoryMg";
		} else {
	        try {
	        	categoryservice.insert(name);
	        } catch (Exception e) {
				model.addAttribute("msg", "255文字以内で入力してください");
				return "categoryMg";
	        }
			List<Category> category = new ArrayList<>();
			category = categoryservice.findAll();
			session.setAttribute("category", category);
			return "categoryMg";

		}
	}

}