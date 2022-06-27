<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おえかきや｜新規登録</title>
<link href="css/commons.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
</head>
<body>

		<header>
		<div class="header">
			<h1>
				<a href="./home" class="page-title">おえかきや</a>
			</h1>
		</div>
		<hr>
	</header>

	<div class="insert">
		<div class="discription">
			<p>
				登録情報を入力してください（<span class="required"></span>は必須です）
			</p>
		</div>

		<div class="form_body">
			<p class="error">
				<c:if test="${not empty registerErrMsg}">
					<span>${fn:escapeXml(registerErrMsg)}</span>
				</c:if>
			</p>

			<form:form action="register" method="post" modelAttribute="signup">
				<fieldset class="label-130">
					<div>
						<label class="required">アカウントID</label>
						<form:input type="text" path="accountId" class="base-text"
							value="${accountId}" pattern="^[0-9a-zA-Z]+$" title="半角英数字で入力してください。" />
						<span class="error"><form:errors path="accountId"
								cssStyle="color: red" /> </span>
					</div>
					<div>
						<label class="required">パスワード</label>
						<form:input type="password" path="password" class="base-text"
							value="${password}" pattern="^[0-9a-zA-Z]+$" title="半角英数字で入力してください。" id="Pas" oninput="CheckPassword(this)"/>
							<span onclick="ChangeVisibleStatus()" class="ViewPoint">👁️</span>
						<span class="error"><form:errors path="password"
								cssStyle="color: red" /></span>
					</div>
					<div>
						<label class="required">パスワード再入力</label>
						<form:input type="password" path="repassword" class="base-text"
							value="${repassword}" pattern="^[0-9a-zA-Z]+$" title="半角英数字で入力してください。" id="Pas2" oninput="CheckPassword(this)"/>
							<span onclick="ChangeVisibleStatus2()" class="ViewPoint">👁️</span>
						<span class="error"><form:errors path="repassword"
								cssStyle="color: red" /></span>
					</div>
					<div>
						<label class="required">名前</label>
						<form:input type="text" path="name" class="base-text"
							value="${name}" />
						<span class="error"><form:errors path="name"
								cssStyle="color: red" /></span>
					</div>
				</fieldset>
				<div class="btns">
					<button type="submit"  name="insert"
						class="basic_btn">
						登録
					</button>
					<input type="button" onclick="location.href='login'" value="キャンセル"
						class="cancel_btn">
				</div>
				
			</form:form>
			
		</div>
	</div>
	<div id="fadeLayer"></div>
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
	
		var preConfirm = null;
		function CheckPassword(confirm) {
			// 入力値取得
			document.getElementById("Pas").setCustomValidity('');
			// 			preConfirm = confirm;
			document.getElementById("Pas2").setCustomValidity('');
			var input1 = Pas.value;
			var input2 = Pas2.value;
			
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
