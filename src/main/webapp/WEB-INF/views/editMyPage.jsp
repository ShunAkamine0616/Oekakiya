<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<title>おえかきや|プロフィール編集</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/commons.css" rel="stylesheet">
<link href="css/image_upload.css" rel="stylesheet">
</head>
<body>
	<div class="header">
		<h1 class="site_logo">
			<a href="menu.jsp">おえかきや</a>
		</h1>
		<div class="user">
			<p class="user_name">${user.name}さん、こんにちは</p>
			<form class="logout_form" action="logout" method="get">
				<button class="logout_btn" type="submit">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>
	<div class="insert">
		<form:form method="post" enctype="multipart/form-data"
			action="editMyPage" modelAttribute="editMyPage" class="upload_form">
			<%-- 		<div>名前：<form:input path="name" /><form:errors path="name" cssStyle="color: red"/></div> --%>
			<%--   <div>ポイント：<form:input path="point" /><form:errors path="point" cssStyle="color: red"/></div> --%>
			<div>
				<div>
					<p>
						名前：
						<form:input path="name" />
						<form:errors path="name" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						アカウントID：

						<form:input path="accountId" />
						<form:errors path="accountId" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						パスワード：

						<form:input type="password" path="password" id="Pas"
							oninput="CheckPassword(this)" />
						<span onclick="ChangeVisibleStatus()" class="ViewPoint">👁</span>
						<form:errors path="password" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						パスワード確認用：

						<form:input type="password" path="passConfirmation" id="Pas2"
							oninput="CheckPassword(this)" />
						<span onclick="ChangeVisibleStatus2()" class="ViewPoint">👁</span>
						<form:errors path="passConfirmation" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						メールアドレス：
						<form:input type="email" path="mail" />
						<form:errors path="mail" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<div>自己紹介：</div>
					<textarea name="introduction"
						placeholder="例&#13;&#10;画像サイズ：367×400&#13;&#10;用途：アイコン&#13;&#10;かわいい感じで書いてみました。アイコンなどに自由に使ってください。"></textarea>
					<form:errors path="introduction" cssStyle="color: red" />
				</div>
				<p>
					<input name="file" type="file" id="file_upload"
						onchange="iconImage(this);"> <input type="submit"
						value="編集完了" class="light_blue_btn" id="editEnd" /> 
						<input
						type="button" onclick="location.href='back'" value="削除"
						class="delete_btn">
						<input
						type="button" onclick="location.href='back'" value="編集取消"
						class="gray_btn">
				</p>
			</div>
			<label for="file_upload">
				<div class="image_border">
					<img id="icon" class="icon"
						src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw=="
						style="max-width: 100px;">
				</div>
			</label>
			<label for="file_upload"> <img id="iconAdd" class="iconAdd"
				src="/images/icon_001050_256.png" style="max-width: 30px;">
			</label>
		</form:form>
	</div>
	<script>
		window
				.addEventListener(
						'load',
						function() {
							document.getElementById('icon').src = "/images/汎用的な人のシルエットアイコン.png";
						});
		function iconImage(obj) {
			var fileReader = new FileReader();
			fileReader.onload = (function() {
				document.getElementById('icon').src = fileReader.result;
			});
			fileReader.readAsDataURL(obj.files[0]);
		}
	</script>
	<script language="javascript" type="text/javascript">
		function ChangeVisibleStatus() {
			var PassType = document.getElementById("Pas");
			if (PassType.type === "password") {
				PassType.type = "text"
			} else if (PassType.type === "text") {
				PassType.type = "password";
			}
		}
	</script>
	<!-- 目のマーク（パスワードの表示/非表示）の処理2 -->
	<script language="javascript" type="text/javascript">
		function ChangeVisibleStatus2() {
			var PassType2 = document.getElementById("Pas2");
			if (PassType2.type === "password") {
				PassType2.type = "text"
			} else if (PassType2.type === "text") {
				PassType2.type = "password";
			}
		}
	</script>
	<!-- パスワードとパスワード確認が一致しているか確認する処理 -->
	<script>
		// 	document.getElementById("editEnd").onclick = function() {
		// 		  // ここに#buttonをクリックしたら発生させる処理を記述する
		// 		  // 入力値取得
		// 			var input1 = document.getElementById("Pas").value;
		// 			var input2 = document.getElementById("Pas2").value;
		// 			// パスワード比較
		// 			if (input1 != input2) {
		// 				confirm.setCustomValidity("入力値が一致しません。");
		// 			} else {
		// 				confirm.setCustomValidity('');
		// 			}
		// 		};
		var preConfirm = null;
		function CheckPassword(confirm) {
			// 入力値取得
			document.getElementById("Pas").setCustomValidity('');
// 			preConfirm = confirm;
			document.getElementById("Pas2").setCustomValidity('');
			var input1 = Pas.value;
			var input2 = Pas2.value;
			// 			if(preConfirm !== confirm) {
			// 				preConfirm.setCustomValidity('');
			// 				preConfirm = confirm;
			// 			}
			// パスワード比較
			if (input1 != input2) {
				confirm.setCustomValidity("入力値が一致しません。");
				// 				preConfirm = confirm;
			} else {
				confirm.setCustomValidity('');
			}
		}
	</script>
</body>
</html>