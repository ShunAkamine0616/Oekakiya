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
    
	<body>
		<div id="app">
			<hr>
			
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
			<div>
				フォロワー：${followCnt.getFollowCount()}人
			</div>
			<c:if test="${ user.getRole() eq '1' }">
				<div class="right">
					
					<div class="btn" style="text-align: right">
						<a class="basic_btn regist" href="/other">アカウント削除</a>
						<a class="basic_btn regist" href="/other">管理者権限を付与</a>
					</div>
				</div>
			</c:if>
			
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
	</body>
</html>