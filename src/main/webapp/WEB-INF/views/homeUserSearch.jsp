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
								<a href="./mypage"> 
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
			<a href="upload" class="post_btn">　投稿　</a>
		</c:if>
		<p>${ msg }</p>
		<form method="get" action="/searchUser" id="target">
			<div class="search_container">
				<input type="text" size="25" name="keyword" id="keyword"
					placeholder="キーワード検索"> <input type="submit" value="&#xf002">
			</div>
			<input type="submit" value="ユーザー" class="not_select_btn">
			<a href="home" class="select_btn">イラスト</a> <br> <br>
			
			<div>
				<label>検索対象：</label> 
				<label> <input type="radio" name="user"
					id="user" value="all" checked onChange="location.href='searchUser?keyword=${ keywordHistory }&user=all'">すべて
				</label>
				<c:if test="${ user ne null }">
					<label> <input type="radio" name="user" id="user"
						value="follow"<c:if test="${ userHistory eq 'follow' }"> checked </c:if>onChange="location.href='searchUser?keyword=${ keywordHistory }&user=follow'">フォロー
					</label>
				</c:if>
			</div>
			<br>
			
		</form>

		
		<c:forEach var="userList" items="${userList}">
			<c:if test="${ user.id ne userList.getId() }">
				<div class="userContainer"><a href="other?id=${ userList.getId() }"><img id="iconAdd" class="image_circle" src="${ userList.getIconPath() }"></a><span class="user_name"><b>${ userList.getName() }</b><br> ${ userList.getAccountId() }</span></div>
			</c:if>
		</c:forEach>
		

	</div>
	<footer></footer>

	<script src="js/home.js"></script>
</body>
</html>