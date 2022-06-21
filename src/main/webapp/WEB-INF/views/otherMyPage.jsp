<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>${userOther.getName()}のマイページ</title>
	<link href="css/commons.css" rel="stylesheet">
	<link href="css/home.css" rel="stylesheet">
	<link
		href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
		rel="stylesheet">
	</head>
	
	<body>
		<div id="app">

			<div class="header">
				<h1 class="site_logo">
					<a href="back">おえかきや</a>
				</h1>
				<div class="user">
					<p class="user_name">${ userMe.getName() }</p>
					<form class="logout_form" action="logout" method="get">
						<button class="logout_btn" type="submit">
							<img src="images/ドアアイコン.png">ログアウト
						</button>
					</form>
				</div>
			</div>
			<hr>
			
			<div  class="site_logo">
				<h1>
					${ userOther.getName() }
				</h1>
				${ userOther.getAccountId() }<br>
				${ userOther.getMail() }<br>
			</div>
			<div>
				フォロワー${follow.getFollow1count}人
			</div>
			<c:if test="${ userMe.getRole() eq '1' }">
				<div class="btn">
					<a class="basic_btn regist" href="/other">アカウント削除</a>
					<a class="basic_btn regist" href="/other">管理者権限を付与</a>
				</div>
			</c:if>
			<c:if test="${imageList}">
			<div class="container">
				<c:forEach var="image" items="${imageList}">
					<div class="box">
						<a href="detail&id=${ image.getId() }"> 
							<img src=${ image.getImagePath() }>
						</a>
						<p>${ image.getImageTitle() }</p>
	
					</div>
				</c:forEach>
			</div>
			</c:if>
		</div>
	</body>
</html>