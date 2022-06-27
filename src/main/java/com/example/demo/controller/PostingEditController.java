
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
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DeleteUserService;
import com.example.demo.service.DownloadService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.FollowService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;

@Controller
public class PostingEditController{
	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	@Autowired
	FollowService followService;
	@Autowired
	FavoriteService favoriteService;
	@Autowired
	DeleteUserService deleteUserService;
	@Autowired
	DownloadService downloadService;
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
		int edit = imageservice.update(image);
		model.addAttribute("edit", edit);
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		Integer follow  = followService.countFollow(user.getId());
		model.addAttribute("followCnt", follow);
		
		List<User> followUser = (List<User>) followService.findByUserIdFollow(user.getId());
		model.addAttribute("followUser", followUser);
		
		List<Image> imageList = (List<Image>) imageService.findByUserId(user.getId());
		model.addAttribute("imageList",imageList);
		
		List<Image> imageFavList = (List<Image>) favoriteService.findByUserId(user.getId());
		model.addAttribute("imageFavList",imageFavList);
		System.out.println(imageFavList);
		
		List<Image> imageDlList = (List<Image>) downloadService.findByUserIdList(user.getId());
		model.addAttribute("imageDlList",imageDlList);
		System.out.println(imageDlList);
		
		return "MyPage";
    
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(@ModelAttribute("postingEdit") EditForm from, Model model) {
		int imageId = (int)session.getAttribute("imageId"); 
		int delete = imageservice.delete(imageId);
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		Integer follow  = followService.countFollow(user.getId());
		model.addAttribute("followCnt", follow);
		
		List<User> followUser = (List<User>) followService.findByUserIdFollow(user.getId());
		model.addAttribute("followUser", followUser);
		
		List<Image> imageList = (List<Image>) imageService.findByUserId(user.getId());
		model.addAttribute("imageList",imageList);
		model.addAttribute("delete",delete);
		
		List<Image> imageFavList = (List<Image>) favoriteService.findByUserId(user.getId());
		model.addAttribute("imageFavList",imageFavList);
		System.out.println(imageFavList);
		
		List<Image> imageDlList = (List<Image>) downloadService.findByUserIdList(user.getId());
		model.addAttribute("imageDlList",imageDlList);
		System.out.println(imageDlList);
		
		return "MyPage";
	}


	
	
}