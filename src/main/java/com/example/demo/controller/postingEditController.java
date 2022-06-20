package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.EditForm;
import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;

@Controller
public class postingEditController{
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private ImageService imageservice;
	@Autowired
	private CategoryService categoryservice;
	
	@RequestMapping("/index")
	public String index(@ModelAttribute("postingEdit") EditForm from, Model model) {
		//後からイメージ愛ディー入れる
		int imageid = 5;
		session.setAttribute("imageid",imageid);
		//渡されたイメージIDをもとに情報を取得
		System.out.println(imageservice.findByImageId(imageid).getCategoryId());
//		from.setCategoryid(imageservice.findByImageId(imageid).getCategoryId());
		from.setComment(imageservice.findByImageId(imageid).getComment());
		Image image= imageservice.findByImageId(imageid);
		List<Category> category = new ArrayList<>();
		category = categoryservice.findAll();
		System.out.println(category.get(0).getCategoryName());
		session.setAttribute("category",category);
		session.setAttribute("image",image);
		return "postingEdit";
	}
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("postingEdit") EditForm form, Model model) {
		Image image =new Image();
		image.setImageTitle(form.getTitle());
		image.setComment(form.getComment());
		image.setCategoryId(form.getCategoryid());
		int imageid = (int)session.getAttribute("imageid");  
		image.setId(imageid);		
		imageservice.update(image);
		return "postingEdit";
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(@ModelAttribute("postingEdit") EditForm from, Model model) {
		int imageid = (int)session.getAttribute("image");  
		imageservice.delete(imageid);
		return "postingEdit";
	}
	
	@RequestMapping(value="/back",method = RequestMethod.GET)
	public String cancel(@ModelAttribute("postingEdit") EditForm from, Model model) {
		//まいぺーじができたら遷移先を決める。
		return "postingEdit";
	}
	
	
}