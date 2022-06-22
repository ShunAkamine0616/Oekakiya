package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
	ImageService imageService;
	
	//マイページ遷移
	@RequestMapping({"/mypage"})
	public String mypage(Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		List<Image> imageList = (List<Image>) imageService.findByUserId(user.getId());
		if(imageList != null) {
			model.addAttribute("imageList",imageList);
			System.out.println(imageList.get(0).getImagePath());
		}
		return "MyPage";
	}
	
	//マイページ編集遷移
	@RequestMapping(path = "inputEditMyPage", method = RequestMethod.GET)
	String uploadview(@ModelAttribute("editMyPage") EditMyPageForm editMyPageForm, Model model) {

		return "editMyPage";
	}
	//マイページ編集処理
	@RequestMapping(path = "editMyPage", method = RequestMethod.POST)
	String editMyPage(@Validated @ModelAttribute("editMyPage") EditMyPageForm editMyPageFrom, BindingResult bindingResult, Model model, HttpServletRequest req) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "editMyPage";
		}
		if (editMyPageFrom.getFile().isEmpty()) {
			return "editMyPage";
		}
		User user = (User) session.getAttribute("user");
		int dot = editMyPageFrom.getFile().getOriginalFilename().lastIndexOf(".");
		//拡張子
		String extention = "";
		if (dot > 0) {
			extention = editMyPageFrom.getFile().getOriginalFilename().substring(dot).toLowerCase();
		}
		// ファイル名
		String filename = editMyPageFrom.getName() + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		System.out.println("filename = " + filename);
		//画像インスタンス生成
		User newUser = new User(editMyPageFrom.getAccountId(), editMyPageFrom.getPassword(), editMyPageFrom.getName(), "images/" + filename + extention, editMyPageFrom.getMail(), editMyPageFrom.getIntroduction());
		newUser.setId(user.getId());
		if(userService.update(newUser) == 0) {
			model.addAttribute("imgErrMsg", "編集できませんでした。");
			return "editMyPage";
		}

		try (BufferedInputStream bis = new BufferedInputStream(editMyPageFrom.getFile().getInputStream());
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\Users\\axiz\\git\\Oekakiya\\src\\main\\webapp\\images\\" + filename + extention))) {

			//読み取ったデータを格納するためのバッファとなるバイト配列を宣言します。
			//配列の長さは、1024の倍数にするのが一般的です。
			byte[] data = new byte[1024];

			//read(byte[] b)メソッドで返ってくる、読み取ったバイト配列の長さを格納するためのint型変数を宣言します。
			int len;
			//read(byte[] b)メソッドはバイト配列に読み取ったバイトの情報をコピーしますが、
			//読み取ったバイト配列の長さをint型で返し、ファイルの終わりに達した場合は-1を返します。
			//そのため、bis.read(data)が-1を返すまでwhileループさせるとファイル全体を読み込むことができます。
			while ((len = bis.read(data)) != -1) {

				//dataをコンソールに出力してみます。
				//配列をコンソール出力するには、Arrays.toString(Object[] obj)を使います。
				System.out.println(Arrays.toString(data));

				//read(byte[] b, int off, int len)メソッドで、readで読み取った長さの分だけ、
				//バイト配列dataの内容をsample3.jpgに書き込みます。
				bos.write(data, 0, len);
				bos.flush();
				System.out.println("filename = " + filename);
				//				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("user", userService.findById(user.getId()));
		return "editMyPage";
	}
}