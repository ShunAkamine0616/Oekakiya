package com.example.demo.controller;

import java.util.Base64;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.UploadForm;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;

@Controller
@MultipartConfig
public class ImageController {
	@Autowired
	HttpSession session;
	@Autowired
	ImageService imageService;
	@Autowired
	CategoryService categoryService;
	// マイページから呼ばれたとき
	@RequestMapping(path = "upload1", method = RequestMethod.GET)
	String uploadview1(@ModelAttribute("upload") UploadForm uploadForm, Model model) {
		session.setAttribute("categoryList", categoryService.findAll());
		session.setAttribute("return1", "mypage");
		return "imagePosting";
	}
	// ホームから呼ばれたとき
	@RequestMapping(path = "upload", method = RequestMethod.GET)
	String uploadview2(@ModelAttribute("upload") UploadForm uploadForm, Model model) {
		session.setAttribute("categoryList", categoryService.findAll());
		session.setAttribute("return1", "home");
		return "imagePosting";
	}

	@RequestMapping(path = "upload1", method = RequestMethod.POST)
	String upload(@Validated @ModelAttribute("upload") UploadForm uploadForm, BindingResult bindingResult, Model model, HttpServletRequest req) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "imagePosting";
		}
		if (uploadForm.getFile().isEmpty()) {
			model.addAttribute("imgErrMsg", "画像が選択されていません。");
			return "imagePosting";
		}
		User user = (User) session.getAttribute("user");
		int dot = uploadForm.getFile().getOriginalFilename().lastIndexOf(".");
		//拡張子
		String extention = "";
		if (dot > 0) {
			extention = uploadForm.getFile().getOriginalFilename().substring(dot).toLowerCase();
		}
		// ファイル名
//		String filename = uploadForm.getImageTitle().replaceAll(" ","").replaceAll("　","") + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		
		try {
			String encodedString = Base64.getEncoder().encodeToString(uploadForm.getFile().getBytes());
			Image image = new Image(uploadForm.getImageTitle(), "data:image/"+ extention +";base64,"+encodedString, extention,uploadForm.getComment(), uploadForm.getCategoryId(), user.getId());
			if(imageService.insert(image) == 0) {
				session.setAttribute("imgErrMsg", "投稿できませんでした。");
				return "imagePosting";
			}
			image = imageService.findByImagePath("data:image/"+ extention +";base64,"+encodedString);
			model.addAttribute("image", image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "postingCompleted";
	}
}
