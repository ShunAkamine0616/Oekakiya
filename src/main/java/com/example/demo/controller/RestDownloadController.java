package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Image;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DownloadService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.ImageService;

@RestController
public class RestDownloadController {
	@Autowired
	HttpSession session;
	@Autowired
	DownloadService downloadService;
	@Autowired
	ImageService imageService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	FavoriteService favoriteService;
	//ダウンロード数をインサート&ダウンロード数を数える
	@GetMapping("DownloadCount")
	public int count(Model model) {
		Image image = (Image)session.getAttribute("image"); 
		Integer userId = (Integer) session.getAttribute("UserId");
		if(userId == null) {
			userId = 0;
		}
		int imageId =image.getId();
		downloadService.insert(userId,imageId);
		int count = downloadService.countDownload(imageId);
		return count;
	}


}
