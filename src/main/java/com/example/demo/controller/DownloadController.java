package com.example.demo.controller;

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
import com.example.demo.service.CategoryService;
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
	@Autowired
	CategoryService categoryService;
	// 登録画面遷移
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(@ModelAttribute("download") DownloadForm downloadform, Model model) {
    	//セッションに保存されたimageIdを取得
    			Image image = (Image) session.getAttribute("image");
//    			Integer imageId = image.getId();
    	session.setAttribute("downloadImg",image);
    	
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
    	//ユーザのセッションを破棄
    	session.removeAttribute("user");
//    	//カテゴリのセッションを保持
//    	ArrayList<Category> categoryList = (ArrayList<Category>) categoryService.findAll();
//		session.setAttribute("category",categoryList);
//		//ダウンロード、いいねカウントのセッションを保持
//		Integer imageId = session.getAtribute("image",);
//		Image count = new Image();
//		count = imageService.findByIdCount(imageId);
//		session.setAttribute("count", count);
        return "home";
    }
    //管理者権限で画像を削除
    @RequestMapping(value="/adminsDelete",method = RequestMethod.GET)
	public String delete(@ModelAttribute("download") DownloadForm downloadform, Model model) {
		Image image = (Image)session.getAttribute("image"); 
		imageService.delete(image.getId());
		return "home";
	}
  
}
