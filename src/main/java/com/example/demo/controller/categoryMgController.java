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
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;

@Controller
public class categoryMgController{
	
	@Autowired
	HttpSession session;
	@Autowired
    private HttpServletRequest req;
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
	@RequestMapping(value = "/categoryEdit",params ="editId" ,method = RequestMethod.POST)
	public String edit(@RequestParam("editId") Integer id,Model model) {
		//categoryを全権取得
		String name = req.getParameter(id.toString());
		if(name.equals("")) {
			model.addAttribute("msg", "カテゴリ名は必須入力項目です。");
			return "categoryMg";
		}else {
		int update = categoryservice.update(id,name);
		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		session.setAttribute("category",category);
		return "categoryMg";
		}
	}
	@RequestMapping(value = "/categoryEdit",params ="deleteId",method = RequestMethod.POST)
	public String delete(@RequestParam("deleteId") Integer[] id,Model model) {
		for(Integer i :id) {
			int delete = categoryservice.delete(i);
			System.out.println(delete);
			model.addAttribute("delete", delete);
			}
		
		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		session.setAttribute("category",category);
		return "categoryMg";
	}

	@RequestMapping(value="/categoryinsert",method = RequestMethod.POST)
	public String insert(@RequestParam("name") String name,Model model) {
		if(name.equals("")) {
			model.addAttribute("msg", "カテゴリ名は必須入力項目です。");
			return "categoryMg";
		}else {
		int insert = categoryservice.insert(name);
		System.out.println(insert);
		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		session.setAttribute("category",category);
		return "categoryMg";
		}
	}

	
}