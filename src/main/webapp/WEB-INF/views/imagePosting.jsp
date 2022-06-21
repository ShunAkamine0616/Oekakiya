<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<title>おえかきや|画像投稿</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/commons.css" rel="stylesheet">
<link href="css/image_upload.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
</head>
<body>
	<!-- 	<div class="header"> -->
	<!-- 		<h1 class="site_logo"> -->
	<!-- 			<a href="menu.jsp">おえかきや</a> -->
	<!-- 		</h1> -->
	<!-- 		<div class="user"> -->
	<%-- 			<p class="user_name">${user.name}さん、こんにちは</p> --%>
	<%-- 			<form class="logout_form" action="logout" method="get"> --%>
	<!-- 				<button class="logout_btn" type="submit"> -->
	<!-- 					<img src="images/ドアアイコン.png">ログアウト -->
	<!-- 				</button> -->
	<%-- 			</form> --%>
	<!-- 		</div> -->
	<!-- 	</div> -->
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
						<div>
							<label> <a href="./inputEditMyPage"> <img
									id="iconAdd" class="iconAdd" src="${user.iconPath}"
									style="max-width: 30px;">
							</a> ${user.name}

							</label>
						</div>
						<button type="button" onclick="location.href='login'"
							class="logout_btn">ログアウト</button>

					</c:when>

				</c:choose>
			</div>
		</div>

	</header>
	<hr>
	<div class="insert">
		<form:form method="post" enctype="multipart/form-data"
			action="upload1" modelAttribute="upload" class="upload_form">
			<%-- 		<div>名前：<form:input path="name" /><form:errors path="name" cssStyle="color: red"/></div> --%>
			<%--   <div>ポイント：<form:input path="point" /><form:errors path="point" cssStyle="color: red"/></div> --%>
			<div>
				<div>
					<p>
						タイトル：
						<form:input path="imageTitle" />
						<form:errors path="imageTitle" cssStyle="color: red" />
					</p>
				</div>
				<%-- 		<form:select path="categoryId" class="base-text"> --%>
				<%-- 			<form:options items="${categoryList}" itemLabel="categoryName" --%>
				<%-- 				itemValue="categoryId" /> --%>
				<%-- 		</form:select> --%>
				<div>
					<p>
						カテゴリ：
						<form:select path="categoryId" class="base-text">
							<form:options items="${categoryList}" itemLabel="categoryName"
								itemValue="id" />
						</form:select>
						<!-- <select name="categoryId">  -->
						<!-- 							<option value="1">季節</option> -->
						<!-- 							<option value="2">イベント</option> -->
						<!-- 							<option value="3">人物</option> -->
						<!-- 							<option value="4">食べ物</option> -->
						<!-- 							<option value="5">学校</option> -->
						<!-- 							<option value="6">生活</option> -->
						<!-- 							<option value="7">医療</option> -->
						<!-- 							<option value="8">社会</option> -->
						<!-- 							<option value="9">スポーツ</option> -->
						<!-- 							<option value="10">自然</option> -->
						<!-- 							<option value="11">建物・地図</option> -->
						<!-- 							<option value="12">メッセージカード</option> -->
						<!-- 							<option value="13">文字マーク</option> -->
						<!-- 							<option value="14">その他</option> -->
						<!-- 						</select> -->
					</p>
				</div>
				<div>
					<div>コメント：</div>
					<textarea name="comment"
						placeholder="例&#13;&#10;画像サイズ：367×400&#13;&#10;用途：アイコン&#13;&#10;かわいい感じで書いてみました。アイコンなどに自由に使ってください。"></textarea>
					<form:errors path="comment" cssStyle="color: red" />
				</div>
				<p>
					<label for="file_upload" class="logout_btn">画像を選択<input
						name="file" type="file" id="file_upload"
						onchange="previewImage(this);">
					</label> <input type="submit" value="投稿する" class="logout_btn" /> <input
						type="button" onclick="location.href='back'" value="投稿取消"
						class="cancel_btn">
				</p>
			</div>
			<label for="file_upload">
				<div>
					<p>
						画像:<br> <img id="preview" class="image_preview"
							src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw=="
							style="max-width: 500px;">

					</p>
				</div>
			</label>
		</form:form>

		<script>
			window
					.addEventListener(
							'load',
							function() {
								document.getElementById('preview').src = "/images/icon_001050_256.png";
							});
			function previewImage(obj) {
				var fileReader = new FileReader();
				fileReader.onload = (function() {
					document.getElementById('preview').src = fileReader.result;
				});
				fileReader.readAsDataURL(obj.files[0]);
			}
		</script>
	</div>
</body>
</html>