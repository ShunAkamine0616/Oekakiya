
package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
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
	private ImageService imageservice;

	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@Validated @ModelAttribute("postingEdit") EditForm form, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return "postingEdit";
		}
		Image image =new Image();
		image.setImageTitle(form.getTitle());
		image.setComment(form.getComment());
		image.setCategoryId(form.getCategoryId());
		int imageId = (int)session.getAttribute("imageId");  
		image.setId(imageId);		
		imageservice.update(image);
	    String page = (String) session.getAttribute("page"); 
	    if(page.equals("home")) {
			ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.findAll();
			session.setAttribute("category",categoryList);
			ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at DESC");
			session.setAttribute("keywordHistory", "");
			session.setAttribute("categoryHistory", " ");
			session.setAttribute("userHistory", "all");
			session.setAttribute("orderHistory", "created_at");
			if(imageList != null) {
				model.addAttribute("imageList",imageList);
			}
	    	return "home";
	    }else {
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
			List<Image> imageList = (List<Image>) imageService.findByUserId(user.getId());
			model.addAttribute("imageList",imageList);
			   return "MyPage";
	    }
	    
	     
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(@ModelAttribute("postingEdit") EditForm from, Model model) {
		int imageId = (int)session.getAttribute("imageId"); 
		imageservice.delete(imageId);
		return "postingEdit";
	}

	@RequestMapping(value = "/mypegeBack", method = RequestMethod.GET)
	public String cancel(@ModelAttribute("postingEdit") EditForm form, Model model) {
		//まいぺーじができたら遷移先を決める。
		return "home";
	}
	
	
}