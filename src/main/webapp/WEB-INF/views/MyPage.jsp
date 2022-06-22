<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>マイページ</title>
	<link href="css/commons.css" rel="stylesheet">
	<link href="css/home.css" rel="stylesheet">
	<link
		href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
		rel="stylesheet">
	</head>
	<header>
			<div class="header">
				<h1>
					<a href="./home" class="page-title">おえかきや</a>
				</h1>
				
				<div class="btn-wrap">
					<c:choose>
						<c:when test="${empty user}">
							ゲスト
							<button type="button" onclick="location.href='login'"
							class="login_btn">ログイン</button>
						</c:when>
						<c:when test="${not empty user}">
							<label>
								<a href="./mypage"> <img id="iconAdd"
									class="image_circle" src="${user.iconPath}">
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
    
	<body>
		<div id="app">
			<div  class="site_logo">
				<h1>
					${ user.getName() }
					
				</h1>
				<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">${ user.getIntroduction() }</div>
				${ user.getAccountId() }<br>
				${ user.getMail() }<br>
			</div>
			<div class="container">
				${ user.getIconPath() }
				<br>
			</div>
			
			<div class="container">
				<br>
				
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
	</body>
</html>