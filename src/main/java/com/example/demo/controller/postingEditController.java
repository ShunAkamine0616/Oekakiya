package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.EditForm;

@Controller
public class postingEditController{
	
	@RequestMapping("/index")
	public String index(@ModelAttribute("postingEdit") EditForm from, Model model) {
		return "postingEdit";
	}
	@RequestMapping(value="/edit",params = "param1", method = RequestMethod.POST)
	public String edit(@ModelAttribute("postingEdit") EditForm from, Model model) {
		return "postingEdit";
	}
	
	
}