<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おえかきや|ホーム</title>
<link rel="icon" href="favicon.ico">
<link href="css/commons.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="dist/snackbar.min.css" />
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--  jQueryをCDNで読み込み  -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>

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
            				<button type="button" onclick="location.href='login'"
								class="login_btn">ログイン</button>
						</c:when>
						<c:when test="${not empty user}">
							<label> <a href="./mypage"> <img id="iconAdd"
									class="image_circle" src="${user.iconPath}">
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
		<input type="hidden" value="${delete}" id="deleteFlag"></input>
		<c:if test="${ user ne null }">
			<a href="upload" class="post_btn"> 　投稿　 </a>
		</c:if>

		<p>${ msg }</p>
		<form method="get" action="/search" id="target">
			<div class="search_container">
				<input type="text" size="25" name="keyword" id="keyword"
					placeholder="キーワード検索"> <input type="submit" value="&#xf002">
			</div>
			<a href="userSearch" class="select_btn">ユーザー</a> <input type="submit"
				value="イラスト" class="not_select_btn"> <br>


			<div class="search_con">
				<div style="margin: auto; margin-left: 40%;">
					<label>検索対象：</label> <label> <input type="radio"
						name="user" id="user" value="all" checked
						onChange="location.href='search?keyword=${ keywordHistory }&user=all&category=${ categoryHistory }&order=${ orderHistory }'">すべて
					</label>
					<c:if test="${ user ne null }">
						<label> <input type="radio" name="user" id="user"
							value="follow"
							<c:if test="${ userHistory eq 'follow' }"> checked </c:if>
							onChange="location.href='search?keyword=${ keywordHistory }&user=follow&category=${ categoryHistory }&order=${ orderHistory }'">フォロー
						</label>
					</c:if>
				</div>

				<div class="order">
					<label for="sort" style="margin-left: 50px;">並び替え</label> <select
						class="base-text center" id="sort" name="order"
						style="background-color: white;"
						onChange="location.href='search?keyword=${ keywordHistory }&user=${ userHistory }&category=${ categoryHistory }&order='+value">
						<option value="created_at DESC"
							<c:if test="${ orderHistory eq 'created_at DESC' }">selected</c:if>>投稿日</option>
						<option value="updated_at DESC"
							<c:if test="${ orderHistory eq 'updated_at DESC' }">selected</c:if>>更新日</option>
						<option value="download DESC"
							<c:if test="${ orderHistory eq 'download DESC' }">selected</c:if>>ダウンロード数</option>
						<option value="favorite DESC"
							<c:if test="${ orderHistory eq 'favorite DESC' }">selected</c:if>>いいね数</option>
					</select>
				</div>
			</div>

			<br>
			<c:if test="${ user.getRole() eq 1 }">
				<a href="categoryMg" class="categoryMg_btn">カテゴリ管理</a>
			</c:if>
			<!--   チェックボックスの表示切替ボタン   -->
			<div class="checkbox-toggle">カテゴリ▼</div>

			<!--   チェックボックス   -->
			<div class="checkboxes">
				<input name="category" type="hidden" value=" ">

				<c:forEach var="category" items="${category}">
					<label> <input type="checkbox" name="category"
						value=${ category.getId() }> ${ category.getCategoryName() }
					</label>
				</c:forEach>

			</div>

		</form>
<hr>
		<c:if test="${ imageList ne null }">
			<div class="container">
				<c:forEach var="image" items="${imageList}">
				<div style="margin: 30px;margin-bottom: 30px;">
					<div class="box">
						<a href="detail?id=${ image.getId() }"> <img
							src=${ image.base64 } class="post">
						</a>
						<p><b>${ fn:escapeXml(image.getImageTitle()) }</b></p>
						
						<div>
						いいね数:<span id="${ image.getId() }"><c:if test="${empty count.getFavorite()}">${ image.getFavorite() }</c:if>${count.getFavorite()}</span><span>ダウンロード数:${ image.getDownload() }</span></div>
						


						
					</div><c:if test="${not empty user and image.getUserId() ne user.getId()}">
							<!--  最初のボタン -->
							<img src="./images/ハート透過.png" id="${ image.getId() }"
								class="favorite_btn nonHurt <c:if test="${image.getFavoriteFlag() eq 1}">hidden</c:if>" style="width: 30px; height: 30px;">
							<!--  いいね状態のボタン -->
							<img src="./images/ピンクハート透過.png" id="${ image.getId() }"
								class="favorite_btn yesHurt <c:if test="${image.getFavoriteFlag() eq 0}">hidden</c:if>" style="width: 30px; height: 30px;">
						</c:if>
						<c:if test="${image.getUserId() eq user.getId()}">
						<div style="width: 30px; height: 37.2px;" class="center"></div>
						</c:if>
						</div>
						
				</c:forEach>
			</div>
		</c:if>
	</div>
	<footer></footer>

	<script src="js/home.js"></script>
	<script src="./dist/snackbar.min.js"></script>
	<script>
		if (document.getElementById("deleteFlag").value === "1") {
			Snackbar.show({
				actionText: '閉じる',text : 'アカウント削除しました。'
			});
		}
	</script>
	<script src="js/homeFavorite.js"></script>
</body>
</html>