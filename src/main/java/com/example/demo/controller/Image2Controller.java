package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.util.Arrays;
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
import com.example.demo.entity.Image2;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.Image2Service;

@Controller
@MultipartConfig
public class Image2Controller {
	@Autowired
	HttpSession session;
	@Autowired
	Image2Service image2Service;
	@Autowired
	CategoryService categoryService;
	// マイページから呼ばれたとき
	@RequestMapping(path = "2upload1", method = RequestMethod.GET)
	String uploadview1(@ModelAttribute("upload") UploadForm uploadForm, Model model) {
		session.setAttribute("categoryList", categoryService.findAll());
		session.setAttribute("return1", "mypage");
		return "imagePosting";
	}
	// ホームから呼ばれたとき
	@RequestMapping(path = "2upload", method = RequestMethod.GET)
	String uploadview2(@ModelAttribute("upload") UploadForm uploadForm, Model model) {
		session.setAttribute("categoryList", categoryService.findAll());
		session.setAttribute("return1", "home");
		return "imagePosting";
	}

	@RequestMapping(path = "2upload1", method = RequestMethod.POST)
	String upload(@Validated @ModelAttribute("upload") UploadForm uploadForm, BindingResult bindingResult, Model model, HttpServletRequest req) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "imagePosting";
		}
		if (uploadForm.getFile().isEmpty()) {
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
		
		
		
		try (BufferedInputStream bis = new BufferedInputStream(uploadForm.getFile().getInputStream());
				) {

			//読み取ったデータを格納するためのバッファとなるバイト配列を宣言します。
			//配列の長さは、1024の倍数にするのが一般的です。
			byte[] data = new byte[1024];
			//画像インスタンス生成
			
			//read(byte[] b)メソッドで返ってくる、読み取ったバイト配列の長さを格納するためのint型変数を宣言します。
			int len;

			//read(byte[] b)メソッドはバイト配列に読み取ったバイトの情報をコピーしますが、
			//読み取ったバイト配列の長さをint型で返し、ファイルの終わりに達した場合は-1を返します。
			//そのため、bis.read(data)が-1を返すまでwhileループさせるとファイル全体を読み込むことができます。
//			String encodedString = null;
			int i = 0;
			while ((len = bis.read(data)) != -1) {
				if(i == 0) {
//					encodedString = Base64.getEncoder().encodeToString(data);
					i++;
				} else {
//					encodedString += Base64.getEncoder().encodeToString(data);

				}
				//dataをコンソールに出力してみます。
				//配列をコンソール出力するには、Arrays.toString(Object[] obj)を使います。
				System.out.println(Arrays.toString(data));
				//read(byte[] b, int off, int len)メソッドで、readで読み取った長さの分だけ、
				//バイト配列dataの内容をsample3.jpgに書き込みます。
//				bos.write(data, 0, len);
//				bos.flush();
				//				bos.close();
			}
			
			String encodedString = Base64.getEncoder().encodeToString(uploadForm.getFile().getBytes());
			Image2 image2 = new Image2(uploadForm.getImageTitle(), "data:image/"+ extention +";base64,"+encodedString, extention,uploadForm.getComment(), uploadForm.getCategoryId(), user.getId());
			if(image2Service.insert(image2) == 0) {
				session.setAttribute("imgErrMsg", "投稿できませんでした。");
				return "imagePosting";
			}
			image2 = image2Service.findByImageBase64("data:image/"+ extention +";base64,"+encodedString);
			model.addAttribute("image", image2);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Image2 image2 = image2Service.findByImageByte(data);
//		model.addAttribute("image", image);
		return "postingCompleted";
	}
}
