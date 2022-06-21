package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.LoginForm;
import com.example.demo.service.UserService;
@Controller
public class SignupController {

//	@RequestMapping("login")
//    public String login(@ModelAttribute("login") LoginForm loginform, Model model) {
//		System.out.println("aaaaaaa");
//        return "login";
//    }
	@Autowired
	UserService userService;
	@RequestMapping(value = "home", method = RequestMethod.POST)
    public String index(@ModelAttribute("login") LoginForm loginform ,Model model) {
		//imageService.findByKeyword("", " ", "id");
		
        return "home";
    }
	
	@RequestMapping("/signup")
    public String index( Model model) {
		
        return "signup";
    }
	@RequestMapping("/termsOfService")
    public String termsOfService( Model model) {
		
        return "termsOfService";
    }
}
