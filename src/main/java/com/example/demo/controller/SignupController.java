package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.LoginForm;
import com.example.demo.controller.form.SignupForm;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
@Controller
public class SignupController {

	//	@RequestMapping("login")
	//    public String login(@ModelAttribute("login") LoginForm loginform, Model model) {
	//		System.out.println("aaaaaaa");
	//        return "login";
	//    }
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	ImageService imageService;
	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String index(@ModelAttribute("login") LoginForm loginform ,Model model) {
		//imageService.findByKeyword("", " ", "id");

		return "home";
	}

	@RequestMapping(value = "signup",method = RequestMethod.GET)
	public String signup1(@ModelAttribute("signup") SignupForm signupform, Model model) {
		
		return "termsOfService";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup2(@ModelAttribute("signup") SignupForm signupform, Model model) {
		
		return "signup";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@Validated @ModelAttribute("signup") SignupForm signupform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		if(userService.findByAccountId(signupform.getAccountId()) != null) {
			model.addAttribute("registerErrMsg", "そのアカウントIDは既に存在します。");
			return "signup";
		}
		User user = new User(signupform.getAccountId(), signupform.getPassword(),signupform.getName());
		user.setIconPath("images/silhouette.png");
		// ユーザー登録失敗
		if(userService.insert(user) == 0) {
			String errMsg = "登録に失敗しました。";
			model.addAttribute("registerErrMsg", errMsg);
			return "signup";
		} else {
			user = userService.findByAccountId(signupform.getAccountId());
			session.setAttribute("user", user);
			ArrayList<Image> imageList = (ArrayList<Image>) imageService.findByKeyword("", " ", "created_at desc",user.getId());
			model.addAttribute("imageList",imageList);
			return "home";
		}
	}

	@RequestMapping("/termsOfService")
	public String termsOfService( Model model) {

		return "termsOfService";
	}
}
