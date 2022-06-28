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

import com.example.demo.controller.form.DownloadForm;
import com.example.demo.controller.form.LoginForm;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DownloadService;
import com.example.demo.service.FollowService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;



@Controller
public class DownloadController {

	@Autowired
	HttpSession session;
	@Autowired
	DownloadService downloadService;
	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	FollowService followService;
	// 登録画面遷移
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(@ModelAttribute("download") DownloadForm downloadform, Model model) {
		//セッションに保存されたimageIdを取得
		Image image = (Image) session.getAttribute("image");
		//    			Integer imageId = image.getId();
		session.setAttribute("downloadImg",image);
		session.setAttribute("postUser", userService.findById(image.getUserId()));
		System.out.println("postUser");
		return "download";
	}
	// ログイン画面遷移
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String insert(@ModelAttribute("login") LoginForm loginform, Model model) {
		return "login";
	}
	// ログアウト処理
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		//ログアウトしてもホームの初期表示が変わらない
		User userInfo = (User) session.getAttribute("user");
		Integer userId =null;
		userId = userInfo.getId();
		ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at DESC", userId);
		 model.addAttribute("imageList",imageList);
		 
		//ユーザのセッションを破棄
		session.removeAttribute("user");
	

		return "home";
	}
	//管理者権限で画像を削除
	@RequestMapping(value="/adminsDelete",method = RequestMethod.GET)
	public String delete(@ModelAttribute("download") DownloadForm downloadform, Model model) {
		Image image = (Image)session.getAttribute("image"); 
		User userOther = userService.findById(image.getUserId());
		 session.setAttribute("userOther", userOther);
		imageService.delete(image.getId());
		
		session.setAttribute("userOther", userOther);
		
		Integer follow  = followService.countFollow(userOther.getId());
		
		model.addAttribute("followCnt", follow);
		
		
		List<Image> imageList = (List<Image>) imageService.findByUserId(userOther.getId());
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
			System.out.println(imageList.get(0).getImagePath());
		}
		return "otherMyPage";
	}

}
