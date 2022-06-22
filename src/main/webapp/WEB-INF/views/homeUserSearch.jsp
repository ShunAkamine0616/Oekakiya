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
<link href="css/header.css" rel="stylesheet">
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

		<header>
			<div class="header">
				<h1>
					<a href="./home" class="page-title">おえかきや</a>
				</h1>

				<div class="btn-wrap">
					<c:choose>
						<c:when test="${empty user}">
							ゲスト
            				<button type="button" onclick="location.href='login'"class="login_btn">ログイン</button>
						</c:when>
						<c:when test="${not empty user}">
							<label>
								<a href="./inputEditMyPage"> 
									<img id="iconAdd" class="image_circle" src="${user.iconPath}">
								</a> ${user.name}
							</label>
							<button type="button" onclick="location.href='logout'"
								class="logout_btn">ログアウト</button>
						</c:when>
					</c:choose>
				</div>
			</div>
			<hr>
		</header>
		<c:if test="${ user ne null }">
			<a href="upload" style="color: black;">投稿</a>
		</c:if>
		<p>${ msg }</p>
		<form method="get" action="/searchUser" id="target">
			<div class="search_container">
				<input type="text" size="25" name="keyword" id="keyword"
					placeholder="キーワード検索"> <input type="submit" value="&#xf002">
			</div>
			<input type="button" value="ユーザー" style="background-color: yellow;"> 
			<input type="button" value="イラスト"> <br> <br>

			<div>
				<label>検索対象：</label> 
				<label> <input type="radio" name="user"
					id="user" value="all" checked onChange="location.href='search?keyword=${ keywordHistory }&user=all&category=${ categoryHistory }&order=${ orderHistory }'">すべて
				</label>
				<c:if test="${ user ne null }">
					<label> <input type="radio" name="user" id="user"
						value="follow"<c:if test="${ userHistory eq 'follow' }"> checked </c:if>onChange="location.href='search?keyword=${ keywordHistory }&user=follow&category=${ categoryHistory }&order=${ orderHistory }'">フォロー
					</label>
				</c:if>
			</div>
			<br>
			
		</form>

		<div class="container">
			<c:forEach var="image" items="${imageList}">
				<div class="box">
					<a href="detail?id=${ image.getId() }"> <img
						src=${ image.getImagePath() }>
					</a>
					<p>${ image.getImageTitle() }</p>
					<p>いいね：${ image.getFavorite() } DL：${ image.getDownload() }</p>
				</div>
			</c:forEach>
		</div>

	</div>
	<footer></footer>

	<script src="js/home.js"></script>
</body>
</html>