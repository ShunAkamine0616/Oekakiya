package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.EditForm;
import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;

@Controller
public class postingEditController{
	
	@Autowired
	private ImageService imageservice;
	
	@RequestMapping("/index")
	public String index(@ModelAttribute("postingEdit") EditForm from, Model model) {
		
		return "postingEdit";
	}
	@RequestMapping(value="/edit",params = "param1", method = RequestMethod.POST)
	public String edit(@ModelAttribute("postingEdit") EditForm form, Model model) {
		Image image =new Image();
		image.setImageTitle(form.getTitle());
		image.setComment(form.getComment());
		image.setCategoryId(form.getCategoryid());
		image.setId(2);		
		imageservice.update(image);
		return "postingEdit";
	}
	
	@RequestMapping(value="/edit",params = "param2", method = RequestMethod.POST)
	public String delete(@ModelAttribute("postingEdit") EditForm from, Model model) {
		imageservice.delete(2);
		return "postingEdit";
	}
	
	@RequestMapping(value="/edit",params = "param3", method = RequestMethod.POST)
	public String cancel(@ModelAttribute("postingEdit") EditForm from, Model model) {
		return "postingEdit";
	}
	
	
}