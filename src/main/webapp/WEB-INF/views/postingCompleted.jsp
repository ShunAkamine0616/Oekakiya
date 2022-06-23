<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/commons.css" rel="stylesheet">
<link href="css/header.css"rel="stylesheet">
<link href="css/postingCompleted.css"rel="stylesheet">
<title>おえかきや｜投稿完了</title>
</head>
<body>

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

						<label> <a href="./inputEditMyPage"> <img id="iconAdd"
								class="image_circle" src="${user.iconPath}">
						</a> ${user.name}

						</label>
						<button type="button" onclick="location.href='login'"
							class="logout_btn">ログアウト</button>

					</c:when>

				</c:choose>
			</div>
		</div>
		<hr>
	</header>

	<!-- 投稿画像 -->
	<div id="app">
	<div class="">
	<img src="${fn:escapeXml(image.imagePath)}" id="postingImg">
	</div>
	</div>
	<br>
	<div class="completeMsg">投稿しました！</div>
	<br>
	<div class="btn" style="text-align: center">
		<a href="https://twitter.com/share?ref_src=twsrc%5Etfw"
			class="twitter-share-button" data-show-count="false">Tweet</a>
		<script async src="https://platform.twitter.com/widgets.js"
			charset="utf-8"></script>

		<!-- コピー対象要素とコピーボタン -->
		<input id="copyTarget" type="text" value="http://localhost:8080/detail?id=${fn:escapeXml(image.id)}" readonly>
		<button onclick="copyToClipboard()">URL</button>
		<a class="basic_btn" href="/home">ホーム</a> <a class="basic_btn"
			href="/mypage">マイページ</a>
	</div>

	<!-- bodyタグ内の下部に以下を入力する -->
	<script>
		function copyToClipboard() {

			// コピー対象をJavaScript上で変数として定義する
			var copyTarget = document.getElementById("copyTarget");

			// コピー対象のテキストを選択する
			copyTarget.select();

			// 選択しているテキストをクリップボードにコピーする
			document.execCommand("Copy");

			// コピーをお知らせする
			alert("コピーできました！ : " + copyTarget.value);
		}
	</script>
</body>
</html>