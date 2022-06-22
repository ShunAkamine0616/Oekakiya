<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/commons.css" rel="stylesheet">
<link rel="stylesheet" href="css/header.css">
<link href="css/download.css" rel="stylesheet">
<link href="css/postingEdit.css" rel="stylesheet">
<title>Insert title here</title>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>おえかきや</title>

<script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
</head>
<body style="background-color: rgb(255, 245, 233);">
	<div class="category">
		<div class="container">
			<div class="item">


				package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.form.LoginForm;

@Controller
public class LogoutController {
	@Autowired
	HttpSession session;
	@RequestMapping("/logout")
	public String index(@ModelAttribute("index") LoginForm loginform, Model model) {
		session.invalidate();
		return "logout";
	}
}

				<br> <br>
				<!--     <hr width=auto class ="header_line"> -->

			</div>
		</div>
	</div>
	<div class="downloadImg">
		<img src="${image.imagePath}">
	</div>
	<div class="item">
		<div class="rightitem">

			タイトル
			<div class="title">${image.imageTitle}</div>
			<br> カテゴリ
			<div class="category">${image.categoryId}</div>
			<br>
			<div class="comment">${image.comment}</div>
		</div>
	</div>
	<c:if test="${user.role == 2}">
		<button type="button" onclick="openModal()">削除</button>
		<div id="modal">
			<p class="modal_message">削除しますか？</p>
			<div class="btns">
				<button type="button" onclick="location.href='/adminsDelete'"
					class="basic_btn">削除</button>
				<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
			</div>
		</div>
	</c:if>
	<label>

		<button class="light_blue_btn">
			<a href="${imageId.imagePath}" download="${imageId.imageTitle}">ダウンロード</a>
		</button>
	</label>
	<div id="fadeLayer"></div>
</body>

</html>
<script src="./js/commons.js">
</script>
