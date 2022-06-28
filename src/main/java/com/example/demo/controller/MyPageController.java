package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

import com.example.demo.controller.form.EditMyPageForm;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.DeleteUserService;
import com.example.demo.service.DownloadService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.FollowService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;

@Controller
@MultipartConfig
public class MyPageController {
	@Autowired
	HttpSession session;
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
	ImageService imageService;

	//マイページ遷移
	@RequestMapping({"/mypage"})
	public String mypage(Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		Integer follow  = followService.countFollow(user.getId());
		model.addAttribute("followCnt", follow);
		
		List<User> followUser = (List<User>) followService.findByUserIdFollow(user.getId());
		model.addAttribute("followUser", followUser);
		
		List<Image> imageList = (List<Image>) imageService.findByUserId(user.getId());
		model.addAttribute("imageList",imageList);
		System.out.println(imageList);
		
		List<Image> imageFavList = (List<Image>) favoriteService.findByUserId(user.getId());
		model.addAttribute("imageFavList",imageFavList);
		System.out.println(imageFavList);
		
		List<Image> imageDlList = (List<Image>) downloadService.findByUserIdList(user.getId());
		model.addAttribute("imageDlList",imageDlList);
		System.out.println(imageDlList);
		
		return "MyPage";
	}

	// マイページ編集遷移
	@RequestMapping(path = "inputEditMyPage", method = RequestMethod.GET)
	String uploadview(@ModelAttribute("editMyPage") EditMyPageForm editMyPageForm, Model model) {

		return "editMyPage";
	}

	// 自分のアカウント削除してホームに遷移
	@RequestMapping(path = "deleteMyAccount", method = RequestMethod.GET)
	String deleteMyAccount(Model model) {
		User user = (User) session.getAttribute("user");
		deleteUserService.delete(user.getId());
		// userのセッションを破棄
		session.removeAttribute("user");
		// 検索画面のデフォルトの画像情報を取得
		ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at DESC", null);
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
		}
		model.addAttribute("delete", 1);
		return "home";
	}

	//マイページ編集処理
	@RequestMapping(path = "editMyPage", method = RequestMethod.POST)
	String editMyPage(@Validated @ModelAttribute("editMyPage") EditMyPageForm editMyPageFrom, BindingResult bindingResult, Model model, HttpServletRequest req) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "editMyPage";
		}
		User user = (User) session.getAttribute("user");
		//拡張子
		String extention = "";
		User newUser = null;
		// アイコンを変更するときとしないときで条件分岐
		if (editMyPageFrom.getFile().isEmpty()) {
			// 元のアイコンパス
			String iconPath = user.getIconPath();
			newUser = new User(editMyPageFrom.getAccountId(), editMyPageFrom.getPassword(), editMyPageFrom.getName(), iconPath, editMyPageFrom.getMail(), editMyPageFrom.getIntroduction());
		} else {
			int dot = editMyPageFrom.getFile().getOriginalFilename().lastIndexOf(".");
			if (dot > 0) {
				extention = editMyPageFrom.getFile().getOriginalFilename().substring(dot).toLowerCase();
			}
			try {
				String encodedString = Base64.getEncoder().encodeToString(editMyPageFrom.getFile().getBytes());
				newUser = new User(editMyPageFrom.getAccountId(), editMyPageFrom.getPassword(), editMyPageFrom.getName(), "data:image/"+ extention +";base64,"+encodedString, editMyPageFrom.getMail(), editMyPageFrom.getIntroduction());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ユーザーのidをセット
		newUser.setId(user.getId());
		// データベースのユーザー情報をアップデート
		if(userService.update(newUser) == 0) {
			model.addAttribute("imgErrMsg", "編集できませんでした。");
			return "editMyPage";
		}

		model.addAttribute("edit", 1);
		session.setAttribute("user", userService.findById(user.getId()));
		
		Integer follow  = followService.countFollow(user.getId());
		model.addAttribute("followCnt", follow);
		
		List<User> followUser = (List<User>) followService.findByUserIdFollow(user.getId());
		model.addAttribute("followUser", followUser);
		
		List<Image> imageList = (List<Image>) imageService.findByUserId(user.getId());
		model.addAttribute("imageList",imageList);
		System.out.println(imageList);
		
		List<Image> imageFavList = (List<Image>) favoriteService.findByUserId(user.getId());
		model.addAttribute("imageFavList",imageFavList);
		System.out.println(imageFavList);
		
		List<Image> imageDlList = (List<Image>) favoriteService.findByUserId(user.getId());
		model.addAttribute("imageDlList",imageDlList);
		System.out.println(imageDlList);
		return "MyPage";
	}
}