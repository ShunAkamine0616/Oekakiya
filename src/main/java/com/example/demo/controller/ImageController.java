package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
	@RequestMapping(path = "upload", method = RequestMethod.GET)
	String uploadview(@ModelAttribute("upload") UploadForm uploadForm, Model model) {
		session.setAttribute("categoryList", categoryService.findAll());
		return "imagePosting";
	}

	@RequestMapping(path = "upload1", method = RequestMethod.POST)
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
		String filename = uploadForm.getImageTitle().replaceAll(" ","").replaceAll("　","") + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		
		//画像インスタンス生成
		Image image = new Image(uploadForm.getImageTitle(), "images/" + filename + extention, uploadForm.getComment(), uploadForm.getCategoryId(), user.getId());
		if(imageService.insert(image) == 0) {
			session.setAttribute("imgErrMsg", "投稿できませんでした。");
			return "imagePosting";
		}
		
		try (BufferedInputStream bis = new BufferedInputStream(uploadForm.getFile().getInputStream());
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
				//				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("image", image);
		return "postingCompleted";
	}
}
