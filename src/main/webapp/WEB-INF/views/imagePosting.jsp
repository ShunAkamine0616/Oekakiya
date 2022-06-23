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
	<form:form method="post" enctype="multipart/form-data" action="upload1"
		modelAttribute="upload" class="upload_form">
		<div>
			<div>
				<p>
					タイトル：
					<form:input path="imageTitle" />
					<form:errors path="imageTitle" cssStyle="color: red" />
				</p>
			</div>
			<div>
				<p>
					カテゴリ：
					<form:select path="categoryId" class="base-text">
						<form:options items="${categoryList}" itemLabel="categoryName"
							itemValue="id" />
					</form:select>
				</p>
			</div>
			<div>
				コメント：<br>
				<textarea name="comment"
					placeholder="例&#13;&#10;画像サイズ：367×400&#13;&#10;用途：アイコン&#13;&#10;かわいい感じで書いてみました。アイコンなどに自由に使ってください。"></textarea>
				<form:errors path="comment" cssStyle="color: red" />
			</div>
			<div>
				<p>
					<input name="file" type="file" id="file_upload"
						onchange="previewImage(this);" class="display-none"> <input
						type="submit" value="投稿する" class="login_btn" /> <input
						type="button" onclick="location.href='${return1}'" value="投稿取消"
						class="gray_btn">
				</p>
			</div>
		</div>

		<div>
			<p>
				画像:<br> <label for="file_upload"> <img id="preview"
					class="image_preview"
					src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw=="
					style="max-width: 500px;" title="クリックで画像を選択">
				</label>

			</p>
		</div>
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
	<!-- 	</div> -->
</body>
</html>