<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おえかきや｜ログイン</title>
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

	<div class="login_form">
		<img src="./images/maruo.png" class="login_logo">
		<p class="error">
			<c:if test="${not empty loginErrMsg}">
				<span>${fn:escapeXml(loginErrMsg)}</span>
			</c:if>
		</p>

		<form:form action="home" modelAttribute="login" method="post">
			<!-- 			<fieldset> -->
			<div class="cp_iptxt">
				<form:input class="base_input" path="accountId" placeholder="ID" />
				<i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
				<div class="error">
					<form:errors path="accountId" cssStyle="color: red" />
				</div>
			</div>

			<div class="cp_iptxt">
				<form:input class="base_input" type="password" path="password"
					placeholder="PASS" />
				<div class="error">
					<form:errors path="password" cssStyle="color: red" />
				</div>
			</div>
			<!-- 			</fieldset> -->
			<button class="basic_btn" name="login">ログイン</button>
		</form:form>

		<form action="termsOfService" method="get">
			<button class="basic_btn" name="register">新規登録</button>
		</form>
	</div>
</body>
</html>