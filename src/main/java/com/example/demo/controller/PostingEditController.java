
package com.example.demo.controller;

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
import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;

@Controller
public class PostingEditController{
	@Autowired
	ImageService imageService;
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
		return "home";
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(@ModelAttribute("postingEdit") EditForm from, Model model) {
		int imageId = (int)session.getAttribute("imageId"); 
		imageservice.delete(imageId);
		return "postingEdit";
	}
	
	@RequestMapping("/te")
	public String e(@ModelAttribute("postingEdit") EditForm from, Model model) {
		Image count = new Image();
		count = imageService.findByIdCount(16);
		int len = Integer.toString(count.getDownload()).length();
		len=7;
//		if(len>7) {
//	    	String countString = Integer.valueOf(count.getDownload()).toString();
//	    	String result = countString.substring(0, 5);
//	    	int num = Integer.parseInt(result);
//	    	double nums = num;
//	    	nums = nums/10;
//	    	System.out.println(nums+"万");
//		}else if(len>6) {
//	    	String countString = Integer.valueOf(count.getDownload()).toString();
//	    	String result = countString.substring(0, 4);
//	    	int num = Integer.parseInt(result);
//	    	double nums = num;
//	    	nums = nums/10;
//	    	System.out.println(nums+"万");
//		}else if(len>5) {
//	    	String countString = Integer.valueOf(count.getDownload()).toString();
//	    	String result = countString.substring(0, 3);
//	    	int num = Integer.parseInt(result);
//	    	double nums = num;
//	    	nums = nums/10;
//	    	System.out.println(nums+"万");
//	    }else if(len>4) {
//	    	String countString = Integer.valueOf(count.getDownload()).toString();
//	    	String result = countString.substring(0, 2);
//	    	int num = Integer.parseInt(result);
//	    	double nums = num;
//	    	nums = nums/10;
//	    	System.out.println(nums+"万");
//}
		
		switch(len) {
		case 5:
	    	String countString = Integer.valueOf(count.getDownload()).toString();
	    	String result = countString.substring(0, 2);
	    	int num = Integer.parseInt(result);
	    	double nums = num;
	    	nums = nums/10;
	    	System.out.println(nums+"万");
		    break;			
		case 6:
	    	countString = Integer.valueOf(count.getDownload()).toString();
	    	result = countString.substring(0, 3);
	    	num = Integer.parseInt(result);
	    	nums = num;
	    	nums = nums/10;
	    	System.out.println(nums+"万");
			break;			
		case(7):
	    	countString = Integer.valueOf(count.getDownload()).toString();
	    	result = countString.substring(0, 4);
	    	num = Integer.parseInt(result);
	    	nums = num;
	    	nums = nums/10;
	    	System.out.println(nums+"万");
			break;			
		case(8):
	        countString = Integer.valueOf(count.getDownload()).toString();
	    	result = countString.substring(0, 5);
	    	num = Integer.parseInt(result);
	    	nums = num;
	    	nums = nums/10;
	    	System.out.println(nums+"万");
			break;			
		}
		
		session.setAttribute("count", count);
		return "postingEdit";
}
	
}