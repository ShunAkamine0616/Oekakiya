package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.DownloadForm;


@Controller
public class DownloadController {
	// 登録画面遷移
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(@ModelAttribute("download") DownloadForm downloadform, Model model) {
        return "download";
    }
 // ログイン画面遷移
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String insert(@ModelAttribute("login") DownloadForm downloadform, Model model) {
        return "login";
    }
}
