package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.form.DownloadForm;
import com.example.demo.service.DownloadService;
import com.example.demo.service.ImageService;



@Controller
public class DownloadController {

	@Autowired
	HttpSession session;
	@Autowired
	DownloadService downloadService;
	@Autowired
	ImageService imageService;
	// 登録画面遷移
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(@ModelAttribute("download") DownloadForm downloadform, Model model) {
    	session.setAttribute("downloadImg",imageService.findByImageId(3));
    	
        return "download";
    }
 // ログイン画面遷移
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String insert(@ModelAttribute("login") DownloadForm downloadform, Model model) {
        return "login";
    }
    
}
