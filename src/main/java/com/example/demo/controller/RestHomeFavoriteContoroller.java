package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.ImageService;
@RestController
public class RestHomeFavoriteContoroller {
	@Autowired
	HttpSession session;
	@Autowired
	FavoriteService favoriteService;
	@Autowired
	ImageService imageService;
	@Autowired
	CategoryService categoryService;
	//いいね数をインサート&いいね数を数える
    @GetMapping("HomeFavoriteCount")
	public int count(@RequestParam("imageId") Integer imageId,Model model) {
		//Image image = (Image)session.getAttribute("image"); 
		int userId = ((User)session.getAttribute("user")).getId();
		//int imageId =image.getId();
		int insertSession =favoriteService.insert(userId,imageId);
		session.setAttribute("FavoriteInsertInfo",insertSession);
		int count = favoriteService.countFavorite(imageId);
		
//		List<> a= favoriteService.findByUserId(userId);
		return count;
	}
    @GetMapping("HomeFavoriteDelete")
  	public int delete(@RequestParam("imageId") Integer imageId,Model model) {
  		//Image image = (Image)session.getAttribute("image"); 
  		int userId = ((User)session.getAttribute("user")).getId();
  		//int imageId =image.getId();
  		int deleteSession =favoriteService.delete(userId,imageId);
  		session.setAttribute("FavoriteInsertInfo",deleteSession);
  		int count = favoriteService.countFavorite(imageId);
  		
//  		List<> a= favoriteService.findByUserId(userId);
  		return count;
  	}
}
