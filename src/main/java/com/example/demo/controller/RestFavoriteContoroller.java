package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Image2;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.ImageService;
@RestController
public class RestFavoriteContoroller {
	@Autowired
	HttpSession session;
	@Autowired
	FavoriteService favoriteService;
	@Autowired
	ImageService imageService;
	@Autowired
	CategoryService categoryService;
	//いいね数をインサート&いいね数を数える
	@GetMapping("checkFavorite")
	public int checkFavorite(Model model) {
		
		Image2 image = (Image2)session.getAttribute("image"); 
		User user = (User) session.getAttribute("user");
		int imageId =image.getId();
		Image2 GetFavoriteUserIdANDImageId =favoriteService.findByUserIdAndImageId(user.getId(),imageId);
		if(GetFavoriteUserIdANDImageId == null) {
			//まだいいねしてない
			return 0;
		} else {
			//既にいいねしている
			return 1;
		}
	}
	
    @GetMapping("FavoriteCount")
	public int count(Model model) {
		Image2 image = (Image2)session.getAttribute("image"); 
		int userId = (int) session.getAttribute("UserId");
		int imageId =image.getId();
		// いいねをつける
		favoriteService.insert(userId,imageId);
		int count = favoriteService.countFavorite(imageId);
//		Image2 GetFavoriteUserIdANDImageId =favoriteService.findByUserIdAndImageId(userId,imageId);
//		session.setAttribute("favoriteUser",GetFavoriteUserIdANDImageId);
//		List<> a= favoriteService.findByUserId(userId);
		return count;
	}
    @GetMapping("FavoriteDelete")
  	public int delete(Model model) {
  		Image2 image = (Image2)session.getAttribute("image"); 
  		int userId = (int) session.getAttribute("UserId");
  		int imageId =image.getId();
  		//いいねを取り消す
  		favoriteService.delete(userId,imageId);
  		int count = favoriteService.countFavorite(imageId);
//  		Image2 GetFavoriteUserIdANDImageId =favoriteService.findByUserIdAndImageId(userId,imageId);
//		session.setAttribute("favoriteUser",GetFavoriteUserIdANDImageId);
//  		List<> a= favoriteService.findByUserId(userId);
  		return count;
  	}
}
