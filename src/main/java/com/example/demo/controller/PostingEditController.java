
package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.EditForm;
import com.example.demo.entity.Image2;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.Image2Service;
import com.example.demo.service.ImageService;

@Controller
public class PostingEditController{
	@Autowired
	ImageService imageService;

	@Autowired
	CategoryService categoryService;
	@Autowired
	HttpSession session;
	
	@Autowired
	private Image2Service image2service;

	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@Validated @ModelAttribute("postingEdit") EditForm form, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return "postingEdit";
		}
		Image2 image =new Image2();
		image.setImageTitle(form.getTitle());
		image.setComment(form.getComment());
		image.setCategoryId(form.getCategoryId());
		int imageId = (int)session.getAttribute("imageId");  
		image.setId(imageId);		
		int edit = image2service.update(image);
		model.addAttribute("edit", edit);
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
			List<Image2> imageList = (List<Image2>) image2service.findByUserId(user.getId());
			model.addAttribute("imageList",imageList);
			   return "MyPage";
    
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(@ModelAttribute("postingEdit") EditForm from, Model model) {
		int imageId = (int)session.getAttribute("imageId"); 
		int delete = image2service.delete(imageId);
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		List<Image2> imageList = (List<Image2>) image2service.findByUserId(user.getId());
		model.addAttribute("imageList",imageList);
		model.addAttribute("delete",delete);
		return "MyPage";
	}


	
	
}