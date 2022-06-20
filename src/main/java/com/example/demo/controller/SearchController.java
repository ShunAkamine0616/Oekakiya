package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Follow;
import com.example.demo.service.FollowService;
@Controller
public class SearchController {
	
	@Autowired
	FollowService followService;
	
	@RequestMapping({ "/", "/home" })
    public String index( Model model) {
		List<Follow> followList = followService.findByUserId(2);
		int follower = followService.countFollow(2);
		int result = followService.insert(1, 2);
		System.out.println("insert結果"+result);
		result = followService.delete(1, 2);
		System.out.println("delete結果"+result);
		result = followService.deleteByUserId(2);
		System.out.println("deleteById結果"+result);
		for(Follow f : followList) {
			System.out.println(f.getFollowUserId());
		}
		System.out.println("2のフォロワー数："+follower);
        return "home";
    }
}