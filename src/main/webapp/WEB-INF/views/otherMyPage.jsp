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
	<link href="css/mypage.css" rel="stylesheet">
	<link
		href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
		rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	<link href="dist/snackbar.min.css" type="text/css" rel="stylesheet">
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
									class="image_circle" src="${fn:escapeXml(user.iconPath)}">
								</a> ${fn:escapeXml(user.name)}
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
	<div class="margin">
	<div class="flexbox">
		<div  class="item">
		<div class="flexbox2">
			<div class="item2">
				<div id="app">
					<div  class="site_logo">
						<h1>
							${ userOther.getName() }
						</h1>
						${ userOther.getAccountId() }<br>
						${ userOther.getMail() }<br>
				</div>
			</div>
			</div>
			<div class="item2">
				<img class="icon" src=${ userOther.getIconPath() }>
				<br>
							<c:if test="${ user.getRole() eq '1' or user.getRole() eq '2'}">
				<div class="container">
					<div class="btn" style="text-align: center">
								<a id="follow" class="basic_btn regist hidden">フォローする</a>
								<a id="lift" class="basic_btn regist hidden">フォロー解除</a>
						
					</div>
				</div>
			</c:if>
			<c:if test="${ user.getRole() eq '1' }">
				<div id="fadeLayer"></div>
			</c:if>
			</div>
			<div class="item2">
			<div class="flexbox3">
			<div class="item3">フォロワー：<span id="followNum">${fn:escapeXml(followCnt)}</span>人</div>
			<div class="item3"><textarea readonly>${ userOther.getIntroduction() }</textarea></div>
			<c:if test="${ user.getRole() eq '1' }">
				<div class="item3">
					<div class="btn" style="text-align: right">
						<input type="button" onclick="openModal()" value="アカウント消去" class="delete_btn">
						<a id="assign" class="basic_btn regist hidden">管理者権限を付与</a>
						<a id="deprivation" class="basic_btn regist hidden">管理者権限を解除</a>
					</div>
					<div id="modal">
						<p class="modal_message">アカウントを削除しますか？</p>
						<div class="btns">
							<a href="/deleteAccount?otherId=${ userOther.getId() }" class="basic_btn"> はい </a>
							<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
						</div>
					</div>
				</div>
			</c:if>
			</div>
			</div>

		</div>
		</div>
		<div class="item">
			<br>
			<div class="otherimage">
			<div class="container">${ userOther.getName() }が投稿した画像</div>
			<div class="container">
				<br>
				<c:forEach var="image" items="${imageList}">
					<div style="margin: 30px;margin-bottom: 30px;">
					<div class="box">
						<a href="detail?id=${ image.getId() }"> 
							<img src=${ image.getImagePath() } class="post">
						</a>
						<p>${ image.getImageTitle() }</p>
					</div>
					</div>
				</c:forEach>
			</div>
			</div>
		</div>
	  
	</div>
	</div>
		<script src="./dist/snackbar.min.js"></script>
		
	</body>
</html>
<script src="./js/commons.js"></script>
<script src="./js/follow.js"></script>
<script src="./js/roleChange.js"></script>
