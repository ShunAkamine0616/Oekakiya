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

</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu">おえかきや</a>
		</h1>
	</div>

	<hr>

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
							value="${accountId}" />
						<span class="error"><form:errors path="accountId"
								cssStyle="color: red" /> </span>
					</div>
					<div>
						<label class="required">パスワード</label>
						<form:input type="password" path="password" class="base-text"
							value="${password}" />
						<span class="error"><form:errors path="password"
								cssStyle="color: red" /></span>
					</div>
					<div>
						<label class="required">パスワード再入力</label>
						<form:input type="password" path="repassword" class="base-text"
							value="${repassword}" />
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
</body>
</html>
<script src="./js/commons.js"></script>
