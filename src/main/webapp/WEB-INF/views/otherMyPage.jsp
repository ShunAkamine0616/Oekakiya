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
	<link href="css/header.css" rel="stylesheet">
	</head>
<!--	<header>
		<div class="header">
			<h1><a href="./home" class="page-title">おえかきや</a></h1>
			
			<div class="btn-wrap">
				<c:choose>
					<c:when test="${empty user}">
						ゲスト
						<button type="button" onclick="location.href='login'" class="login_btn">ログイン</button>
					</c:when>
					
					<c:when test="${not empty user}">
						<label>
							<a href="./inputEditMyPage">
								<img id="iconAdd" class="iconAdd"
								src="${user.iconPath}" style="max-width: 30px;">
							</a>
							${user.name}
						</label>
						<button type="button" onclick="location.href='login'"  class="logout_btn">ログアウト</button>
					</c:when>
				</c:choose>
			</div>
		</div>
	</header>
-->    
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
					${ userOther.getName() }
					
				</h1>
				<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">${ userOther.getIntroduction() }</div>
				${ userOther.getAccountId() }<br>
				${ userOther.getMail() }<br>
			</div>
			<div class="">
				<img src=${ userOther.getIconPath() }>
				<br>
			</div>
			<div class="container">
				<div class="btn" style="text-align: center">
							<a class="basic_btn regist" href="/other">フォローする</a>
							<br>
							フォロワー：${followCnt.getFollowCount()}人
				</div>
			</div>
			<c:if test="${ user.getRole() eq '1' }">
				<div class="right">
					
					<div class="btn" style="text-align: right">
						<!-- <a class="basic_btn regist" href="openModal()">アカウント削除</a>-->
						<input type="button" onclick="openModal()" value="削除" class="delete_btn">
						<a class="basic_btn regist" href="/other">管理者権限を付与</a>
					</div>
					<div id="modal">
						<p class="modal_message">アカウントを削除しますか？</p>
						<div class="btns">
							<!-- 					<button type="submit" class="basic_btn"> -->
							<!-- 						はい -->
							<!-- 					</button> -->
							<a href="/deleteAccount?otherId=${ userOther.getId() }" class="basic_btn"> はい </a>
							<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
						</div>
					</div>
				</div>
				<div id="fadeLayer"></div>
			</c:if>
			<br>
			<div class="container">${ userOther.getName() }が投稿した画像</div>
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
<script src="./js/commons.js"></script>