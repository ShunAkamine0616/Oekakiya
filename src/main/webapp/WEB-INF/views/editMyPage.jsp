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

	<input id="iconPath" type="hidden" value="${user.iconPath}"></input>
	<div class="insert">
		<p class="error">
			<c:if test="${not empty editMyPageErrMsg}">
				<span>${fn:escapeXml(editMyPageErrMsg)}</span>
			</c:if>
		</p>
		<form:form method="post" enctype="multipart/form-data"
			action="editMyPage" modelAttribute="editMyPage" class="upload_form">
			<%-- 		<div>名前：<form:input path="name" /><form:errors path="name" cssStyle="color: red"/></div> --%>
			<%--   <div>ポイント：<form:input path="point" /><form:errors path="point" cssStyle="color: red"/></div> --%>
			<div>
				<div>
					<p>
						名前：
						<form:input path="name" value="${fn:escapeXml(user.name)}" />
						<form:errors path="name" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						アカウントID：

						<form:input path="accountId"
							value="${fn:escapeXml(user.accountId)}" />
						<form:errors path="accountId" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						パスワード：

						<form:input type="password" path="password" id="Pas"
							oninput="CheckPassword(this)"
							value="${fn:escapeXml(user.password)}" />
						<span onclick="ChangeVisibleStatus()" class="ViewPoint">👁</span>
						<form:errors path="password" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						パスワード確認用：

						<form:input type="password" path="passConfirmation" id="Pas2"
							oninput="CheckPassword(this)"
							value="${fn:escapeXml(user.password)}" />
						<span onclick="ChangeVisibleStatus2()" class="ViewPoint">👁</span>
						<form:errors path="passConfirmation" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<p>
						メールアドレス：
						<form:input type="email" path="mail"
							value="${fn:escapeXml(user.mail)}" />
						<form:errors path="mail" cssStyle="color: red" />
					</p>
				</div>
				<div>
					<div>自己紹介：</div>
					<textarea name="introduction"
						placeholder="ここに自己紹介を書いてください。"
						value="${fn:escapeXml(user.introduction)}"></textarea>
					<form:errors path="introduction" cssStyle="color: red" />
				</div>
				<p>
					<input name="file" type="file" id="file_upload"
						onchange="iconImage(this);"> <input type="submit"
						value="編集完了" class="light_blue_btn" id="editEnd" /> <input
						type="button" onclick="openModal()" value="削除" class="delete_btn">
					<!-- 						<input -->
					<!-- 						type="button" onclick="location.href='home'" value="削除" -->
					<!-- 						class="delete_btn"> -->
					<input type="button" onclick="location.href='home'" value="編集取消"
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
			<div id="modal">
				<p class="modal_message">アカウントを削除しますか？</p>
				<div class="btns">
					<!-- 					<button type="submit" class="basic_btn"> -->
					<!-- 						はい -->
					<!-- 					</button> -->
					<a href="/deleteMyAccount" class="basic_btn"> はい </a>
					<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
				</div>
			</div>
		</form:form>
	</div>
	</div>
	<div id="fadeLayer"></div>
	<script>
		var iconPath = document.getElementById("iconPath").value;
		window
				.addEventListener(
						'load',
						function() {
							if (iconPath === null || iconPath === "") {
								document.getElementById('icon').src = "/images/汎用的な人のシルエットアイコン.png";
							} else {
								document.getElementById('icon').src = iconPath;
							}
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
<script src="./js/commons.js"></script>