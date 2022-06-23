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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.form.EditForm;
import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DownloadService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;


@Controller
public class ImageDetailController {

	@Autowired
	HttpSession session;
	@Autowired
	ImageService imageService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private UserService userService;
	@Autowired
	FavoriteService favoriteService;
	@RequestMapping(path="/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("id") Integer imageId,@ModelAttribute("postingEdit") EditForm form, Model model) {


		//ユーザー情報を取得
		User user = (User) session.getAttribute("user");
		Image count = new Image();
		count = imageService.findByIdCount(imageId);
		session.setAttribute("count", count);
		if(user == null){
			//imageIdから投稿情報を取得
			Image image = imageService.findByImageId(imageId);
			session.setAttribute("image",image);
			return"download";
		} else {
			Integer users = user.getId();
			//ユーザーIDを保存
			session.setAttribute("UserId", users);


			//セッションに保存されたuserIdを取得
			int userId = (int) session.getAttribute("UserId");
			//imageIdから投稿情報を取得
			Image image = imageService.findByImageId(imageId);
			//投稿者かどうかチェック
			if(userId == image.getUserId()) {
				//投稿編集画面へ
				//渡されたイメージIDをもとに情報を取得
				form.setCategoryId(imageService.findByImageId(imageId).getCategoryId());
				form.setComment(imageService.findByImageId(imageId).getComment());
				Image images= imageService.findByImageId(imageId);

				//イメージIDを保存
				session.setAttribute("imageId",imageId);
				//categoryを全権取得
				List<Category> category = new ArrayList<>();
				category = categoryService.findAll();
				session.setAttribute("category",category);
				session.setAttribute("images",images);
				return "postingEdit";
			}else{
				Image DlImages =imageService.findByImageId(imageId);
				// 画像の投稿者を取得
				User postingUser = userService.findById(DlImages.getUserId());
				Category categoryName = categoryService.findByCategoryId(DlImages.getUserId());
				Image GetFavoriteUserIdANDImageId =favoriteService.findByUserIdAndImageId(user.getId(),image.getId());
				session.setAttribute("image",DlImages);
				session.setAttribute("favoriteUser",GetFavoriteUserIdANDImageId);
				session.setAttribute("imageUser",postingUser);
				session.setAttribute("categoryName",categoryName);
				return"download";
				//投稿詳細画面へ
			}
		}

	}
}