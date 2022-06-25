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
import com.example.demo.entity.Image2;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DownloadService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.Image2Service;
import com.example.demo.service.UserService;


@Controller
public class ImageDetailController {

	@Autowired
	HttpSession session;
	@Autowired
	Image2Service imageService;
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


		Image2 count = new Image2();
		count = imageService.findByIdCount(imageId);
		int dounladLen = Integer.toString(count.getDownload()).length();
		int favoriteLen = Integer.toString(count.getFavorite()).length();
		String downloadcount = "0";
		String favoritecount = "0";

		//download数桁数判断
		switch(dounladLen) {
		case 5:
			String countString = Integer.valueOf(count.getDownload()).toString();
			String result = countString.substring(0, 2);
			int num = Integer.parseInt(result);
			double nums = num;
			nums = nums/10;
			downloadcount = nums+"万";
			break;			
		case 6:
			countString = Integer.valueOf(count.getDownload()).toString();
			result = countString.substring(0, 3);
			num = Integer.parseInt(result);
			nums = num;
			nums = nums/10;
			downloadcount = nums+"万";
			break;			
		case(7):
			countString = Integer.valueOf(count.getDownload()).toString();
		result = countString.substring(0, 4);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		downloadcount = nums+"万";
		break;			
		case(8):
			countString = Integer.valueOf(count.getDownload()).toString();
		result = countString.substring(0, 5);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		downloadcount = nums+"万";
		break;	
		default:
			downloadcount = String.valueOf(count.getDownload());
			break;
		}
		session.setAttribute("downloadcount", downloadcount);

		//favorite数桁数判断
		switch(favoriteLen) {
		case 5:
			String countString = Integer.valueOf(count.getFavorite()).toString();
			String result = countString.substring(0, 2);
			int num = Integer.parseInt(result);
			double nums = num;
			nums = nums/10;
			favoritecount = nums+"万";
			break;			
		case 6:
			countString = Integer.valueOf(count.getFavorite()).toString();
			result = countString.substring(0, 3);
			num = Integer.parseInt(result);
			nums = num;
			nums = nums/10;
			favoritecount = nums+"万";
			break;			
		case(7):
			countString = Integer.valueOf(count.getFavorite()).toString();
		result = countString.substring(0, 4);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		favoritecount = nums+"万";
		break;			
		case(8):
			countString = Integer.valueOf(count.getFavorite()).toString();
		result = countString.substring(0, 5);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		favoritecount = nums+"万";
		break;	
		default:
			favoritecount = String.valueOf(count.getFavorite());
			break;
		}
		session.setAttribute("favoritecount", favoritecount);
		session.setAttribute("page", "home");
		if(user == null){
			//imageIdから投稿情報を取得
			Image2 image = imageService.findByImageId(imageId);
			session.setAttribute("image",image);
			// 画像の投稿者を取得
			User postingUser = userService.findById(image.getUserId());
			session.setAttribute("imageUser",postingUser);
			return"download";
		} else {
			Integer users = user.getId();
			//ユーザーIDを保存
			session.setAttribute("UserId", users);


			//セッションに保存されたuserIdを取得
			int userId = (int) session.getAttribute("UserId");
			//imageIdから投稿情報を取得
			Image2 image = imageService.findByImageId(imageId);
			//投稿者かどうかチェック
			if(userId == image.getUserId()) {
				//投稿編集画面へ
				//渡されたイメージIDをもとに情報を取得
				form.setCategoryId(imageService.findByImageId(imageId).getCategoryId());
				form.setComment(imageService.findByImageId(imageId).getComment());
				Image2 images= imageService.findByImageId(imageId);




				//イメージIDを保存
				session.setAttribute("imageId",imageId);
				//categoryを全権取得
				List<Category> category = new ArrayList<>();
				category = categoryService.findAll();
				session.setAttribute("category",category);
				session.setAttribute("images",images);
				return "postingEdit";
			}else{

				Image2 DlImages =imageService.findByImageId(imageId);
				// 画像の投稿者を取得
				User postingUser = userService.findById(DlImages.getUserId());
				Category categoryName = categoryService.findByCategoryId(DlImages.getUserId());
				Image2 GetFavoriteUserIdANDImageId =favoriteService.findByUserIdAndImageId(user.getId(),image.getId());
				session.setAttribute("image",DlImages);
				session.setAttribute("favoriteUser",GetFavoriteUserIdANDImageId);
				session.setAttribute("imageUser",postingUser);
				session.setAttribute("categoryName",categoryName);
				return"download";

			}
		}

	}
	@RequestMapping(path="/detailmyapage", method = RequestMethod.GET)
	public String detailMyaPage(@RequestParam("id") Integer imageId,@ModelAttribute("postingEdit") EditForm form, Model model) {
		
		
		//ユーザー情報を取得
		User user = (User) session.getAttribute("user");
		
		
		Image2 count = new Image2();
		count = imageService.findByIdCount(imageId);
		int dounladLen = Integer.toString(count.getDownload()).length();
		int favoriteLen = Integer.toString(count.getFavorite()).length();
		String downloadcount = "0";
		String favoritecount = "0";
		
		//download数桁数判断
		switch(dounladLen) {
		case 5:
			String countString = Integer.valueOf(count.getDownload()).toString();
			String result = countString.substring(0, 2);
			int num = Integer.parseInt(result);
			double nums = num;
			nums = nums/10;
			downloadcount = nums+"万";
			break;			
		case 6:
			countString = Integer.valueOf(count.getDownload()).toString();
			result = countString.substring(0, 3);
			num = Integer.parseInt(result);
			nums = num;
			nums = nums/10;
			downloadcount = nums+"万";
			break;			
		case(7):
			countString = Integer.valueOf(count.getDownload()).toString();
		result = countString.substring(0, 4);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		downloadcount = nums+"万";
		break;			
		case(8):
			countString = Integer.valueOf(count.getDownload()).toString();
		result = countString.substring(0, 5);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		downloadcount = nums+"万";
		break;	
		default:
			downloadcount = String.valueOf(count.getDownload());
			break;
		}
		session.setAttribute("downloadcount", downloadcount);
		
		//favorite数桁数判断
		switch(favoriteLen) {
		case 5:
			String countString = Integer.valueOf(count.getFavorite()).toString();
			String result = countString.substring(0, 2);
			int num = Integer.parseInt(result);
			double nums = num;
			nums = nums/10;
			favoritecount = nums+"万";
			break;			
		case 6:
			countString = Integer.valueOf(count.getFavorite()).toString();
			result = countString.substring(0, 3);
			num = Integer.parseInt(result);
			nums = num;
			nums = nums/10;
			favoritecount = nums+"万";
			break;			
		case(7):
			countString = Integer.valueOf(count.getFavorite()).toString();
		result = countString.substring(0, 4);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		favoritecount = nums+"万";
		break;			
		case(8):
			countString = Integer.valueOf(count.getFavorite()).toString();
		result = countString.substring(0, 5);
		num = Integer.parseInt(result);
		nums = num;
		nums = nums/10;
		favoritecount = nums+"万";
		break;	
		default:
			favoritecount = String.valueOf(count.getFavorite());
			break;
		}
		session.setAttribute("favoritecount", favoritecount);
		session.setAttribute("page", "mypage");
		
		if(user == null){
			//imageIdから投稿情報を取得
			Image2 image = imageService.findByImageId(imageId);
			session.setAttribute("image",image);
			// 画像の投稿者を取得
			User postingUser = userService.findById(image.getUserId());
			session.setAttribute("imageUser",postingUser);
			return"download";
		} else {
			Integer users = user.getId();
			//ユーザーIDを保存
			session.setAttribute("UserId", users);
			
			
			//セッションに保存されたuserIdを取得
			int userId = (int) session.getAttribute("UserId");
			//imageIdから投稿情報を取得
			Image2 image = imageService.findByImageId(imageId);
			//投稿者かどうかチェック
			if(userId == image.getUserId()) {
				//投稿編集画面へ
				//渡されたイメージIDをもとに情報を取得
				form.setCategoryId(imageService.findByImageId(imageId).getCategoryId());
				form.setComment(imageService.findByImageId(imageId).getComment());
				Image2 images= imageService.findByImageId(imageId);
				
				
				
				
				//イメージIDを保存
				session.setAttribute("imageId",imageId);
				//categoryを全権取得
				List<Category> category = new ArrayList<>();
				category = categoryService.findAll();
				session.setAttribute("category",category);
				session.setAttribute("images",images);
				return "postingEdit";
			}else{
				
				Image2 DlImages =imageService.findByImageId(imageId);
				// 画像の投稿者を取得
				User postingUser = userService.findById(DlImages.getUserId());
				Category categoryName = categoryService.findByCategoryId(DlImages.getUserId());
				Image2 GetFavoriteUserIdANDImageId =favoriteService.findByUserIdAndImageId(user.getId(),image.getId());
				session.setAttribute("image",DlImages);
				session.setAttribute("favoriteUser",GetFavoriteUserIdANDImageId);
				session.setAttribute("imageUser",postingUser);
				session.setAttribute("categoryName",categoryName);
				return"download";
				
			}
		}
		
	}
}