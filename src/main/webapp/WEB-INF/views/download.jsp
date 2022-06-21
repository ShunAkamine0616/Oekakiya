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
<link rel="stylesheet" href="css/header.css">
<link href="css/download.css" rel="stylesheet">
<title>Insert title here</title>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>おえかきや</title>

<script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
</head>
<body style="background-color: rgb(255, 245, 233);">
	<div class="category">
		<div class="container">
			<div class="item">
			

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

									<div class="header_icon"><label> <a href="./inputEditMyPage"> <img
											id="iconAdd" class="iconAdd" src="${user.iconPath}"
											style="max-width: 30px;">
									</a> ${user.name}

									</label></div>
									<button type="button" onclick="location.href='login'"
										class="logout_btn">ログアウト</button>

								</c:when>

							</c:choose>
						</div>
					</div>

				</header>

				<br> <br>
				<!--     <hr width=auto class ="header_line"> -->

			</div>
		</div>
	</div>
	<div class="downloadImg">
		<img src="${downloadImg.imagePath}">
	</div>
	<div class="downloadInfo">
		タイトル <span class=Lavel>${downloadImg.imageTitle}</span><br> カテゴリ<span
			class=Lavel>${downloadImg.categoryId}</span><br>
		${downloadImg.comment}
	</div>
	<c:if test="${user.role == 2}">
		<button class="light_blue_btn">
			<a href="${downloadImg.imagePath}"
				download="${downloadImg.imageTitle}">削除</a>
		</button>
	</c:if>
	<label>

		<button class="light_blue_btn">
			<a href="${downloadImg.imagePath}"
				download="${downloadImg.imageTitle}">ダウンロード</a>
		</button>
	</label>

</body>
</html>