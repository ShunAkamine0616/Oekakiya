package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;


@Controller
public class ImageDetailController {
	
	@Autowired
	HttpSession session;
	@Autowired
	ImageService imageService;
	
	@RequestMapping("/detail")
    public String detail(@RequestParam("id") Integer imageId, Model model) {
		//仮のユーザーIDを保存
		session.setAttribute("UserId", 1);
		//セッションに保存されたuserIdを取得
		int userId = (int) session.getAttribute("UserId");
		//imageIdから投稿情報を取得
		Image image = imageService.findByImageId(imageId);
		//投稿者かどうかチェック
		if(userId == image.getUserId()) {
			//投稿編集画面へ
			
		}else {
			//投稿詳細画面へ
		}
		
        return "";
    }
}