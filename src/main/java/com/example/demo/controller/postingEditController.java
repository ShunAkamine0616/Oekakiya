package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.EditForm;
import com.example.demo.service.FavoriteService;

@Controller
public class postingEditController{
	
	@Autowired
	private FavoriteService favoriteservice;
	
	@RequestMapping("/index")
	public String index(@ModelAttribute("postingEdit") EditForm from, Model model) {
		
		return "postingEdit";
	}
	@RequestMapping(value="/edit",params = "param1", method = RequestMethod.POST)
	public String edit(@ModelAttribute("postingEdit") EditForm from, Model model) {
		return "postingEdit";
	}
	
	@RequestMapping(value="/edit",params = "param2", method = RequestMethod.POST)
	public String delete(@ModelAttribute("postingEdit") EditForm from, Model model) {
		return "postingEdit";
	}
	
	@RequestMapping(value="/edit",params = "param3", method = RequestMethod.POST)
	public String cancel(@ModelAttribute("postingEdit") EditForm from, Model model) {
		return "postingEdit";
	}
	
	
}