<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--  jQueryをCDNで読み込み  -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


</head>
<body>
	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="back">商品管理システム</a>
			</h1>
			<div class="user">
				<p class="user_name">${ userInfo.getName() }さん、こんにちは</p>
				<form class="logout_form" action="logout" method="get">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png">ログアウト
					</button>
				</form>
			</div>
		</div>

		<hr>
		<c:if test="${ userInfo.getRole() eq '1' }">
			<div class="btn">
				<a class="basic_btn regist" href="/insert">新規登録</a>
			</div>
		</c:if>
		<p>${ msg }</p>
		<form method="get" action="/search">
			<div class="search_container">
				<input type="text" size="25" name="keyword" placeholder="キーワード検索">
				<input type="submit" value="&#xf002">
			</div>
			<input type="button" value="ユーザー"> <input type="button"
				value="イラスト"> <br> <br>
			<div>
				<label>検索対象：</label> <label> <input type="radio" name="user"
					value="all" checked>すべて
				</label> <label> <input type="radio" name="user" value="follow">フォロー
				</label>
			</div>
			<br>

			<!--   チェックボックスの表示切替ボタン   -->
			<div class="checkbox-toggle">カテゴリ▼</div>
			<!--   チェックボックス   -->
			<div class="checkboxes">
				カテゴリを選択してください <input name="category" type="hidden" value=" ">
				<label> <input type="checkbox" name="category" value=1>
					<span>checkbox1</span>
				</label> <label> <input type="checkbox" name="category" value=2>
					<span>checkbox2</span>
				</label> <label> <input type="checkbox" name="category" value=3>
					<span>checkbox3</span>
				</label>
			</div>


			<div class="order">
				<label for="sort">並び替え</label>
				<select class="base-text center" id="sort" name="order" style="background-color: white;">
					<option value="created_at" selected>投稿日</option>
					<option value="updated_at">更新日</option>
					<option value="download">ダウンロード数</option>
					<option value="favorite">いいね数</option>
				</select>
			</div>
		</form>

		<div class="container">
			<c:forEach var="image" items="${imageList}">
				<div class="box">
					<a href="detail?id=${ image.getId() }"> 
						<img src=${ image.getImagePath() }>
					</a>
					<p>${ image.getImageTitle() }</p>

				</div>
			</c:forEach>
		</div>

	</div>
	<footer></footer>

	<script src="js/home.js"></script>
</body>
</html>