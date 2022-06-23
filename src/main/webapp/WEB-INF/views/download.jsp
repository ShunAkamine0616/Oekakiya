<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/commons.css" rel="stylesheet">
<link href="css/download.css" rel="stylesheet">
<link href="css/postingEdit.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<title>Insert title here</title>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>おえかきや</title>

<script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
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

					<label> <a href="./mypage"> <img id="iconAdd"
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
<!--  <div class="container"> -->
<!--    <div class="item"> -->
<!--     	<div class="leftitem">			 -->
<!-- 				    <hr width=auto class ="header_line"> -->

<%-- 	<span><img src="./images/images_yesHurt.png" width="3%" height="3%">いいね数:</span><span id="favoriteNum">${count.getFavorite()}</span><span>ダウンロード数:</span><span id="downloadNum">${ count.getDownload() }</span> --%>
<!-- 	<div class="downloadImg"> -->
<%-- 		<img src="${image.imagePath}"> --%>
<!-- 	</div> -->


<!-- 			タイトル -->
<%-- 			<div class="title">${image.imageTitle}</div> --%>
<!-- 			<br> カテゴリ -->
<%-- 			<div class="category">${image.categoryId}</div> --%>
<!-- 			<br> -->
<%-- 			<div class="comment">${image.comment}</div> --%>
<!-- 	</div> -->
<%-- 	<c:if test="${user.role == 2}"> --%>
<!-- 		<button type="button" onclick="openModal()">削除</button> -->
<!-- 		<div id="modal"> -->
<!-- 			<p class="modal_message">削除しますか？</p> -->
<!-- 			<div class="btns"> -->
<!-- 				<button type="button" onclick="location.href='/adminsDelete'" -->
<!-- 					class="basic_btn">削除</button> -->
<!-- 				<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button> -->
<!-- 			</div> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>

<!-- 	<label> -->
<!-- 		<button  id="iineBtn"> -->
<!-- <!-- 			<img src="./images/images_yesHurt.png" id="">いいね -->
<!-- 			<img src="./images/images_nonHurt.png"> -->
<!-- 		</button> -->
<!-- 	</label> -->


<!-- 	<label> -->
<!-- 		<button  id="onIineBtn"> -->
<!-- 			<img src="./images/images_yesHurt.png" > -->
<!-- <!-- 			<img src="./images/images_nonHurt.png"> -->
<!-- 		</button> -->
<!-- 	</label> -->
<!-- 	<label> -->

<!-- 		<button class="light_blue_btn" id="download_btn"> -->
<%-- 			<a href="${image.imagePath}" download="${image.imageTitle}">ダウンロード</a> --%>
<!-- 		</button> -->
<!-- 	</label> -->
<div class="container">
	<div class="item">
		<div class="leftitem">
			<div class="">
				<div class="box">
					<img src="${fn:escapeXml(image.imagePath)}">
				</div>
			</div>
			<span><img src="./images/images_yesHurt.png" width="3%"
				height="3%">いいね数:</span><span id="favoriteNum">${fn:escapeXml(count.getFavorite())}</span><span>ダウンロード数:</span><span
				id="downloadNum">${fn:escapeXml(count.getDownload()) }</span>


			<c:if test="${not empty user}">

				<!--いいねしてないとき -->
				<img src="./images/ハート透過.png" id="nonHurt" class="hidden">
				<!-- いいねしてるとき -->
				<img src="./images/ピンクハート透過.png" id="yesHurt" class="hidden">

			</c:if>
			<c:if test="${user.role == 1}">
				<button type="button" class="delete_btn" onclick="openModal()">削除</button>
				<div id="modal">
					<p class="modal_message">削除しますか？</p>
					<div class="btns">
						<button type="button" onclick="location.href='/adminsDelete'"
							class="basic_btn">削除</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
			</c:if>
			<a href="${fn:escapeXml(image.imagePath)}" download="${fn:escapeXml(image.imageTitle)}">
				<button class="light_blue_btn" id="download_btn">ダウンロード</button>
			</a>


		</div>
	</div>

	<div class="item">
		<div class="rightitem">
			<div class="title">
				<label>タイトル</label> ${fn:escapeXml(image.imageTitle)}


			</div>

			<div class="title">
				<label>投稿者</label> <img src="${fn:escapeXml(imageUser.iconPath)}"
					class="image_circle">${fn:escapeXml(imageUser.name)}

				<div class="category">
					<label>カテゴリ</label> ${fn:escapeXml(categoryName.categoryName)}


				</div>
				<div class="comment">${fn:escapeXml(image.comment)}</div>
			</div>
		</div>

	</div>
	<div id="fadeLayer"></div>
	</body>
</html>
<script src="./js/commons.js">
	
</script>
<script src="./js/download.js">
	
</script>
<script src="./js/favorite.js">
	
</script>
