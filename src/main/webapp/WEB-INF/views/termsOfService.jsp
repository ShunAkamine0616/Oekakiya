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
<title>おえかきや｜利用規約</title>
<link href="css/commons.css" rel="stylesheet">

</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu">おえかきや</a>
		</h1>
	</div>

	<hr>
	<div class="center">
		<iframe src="html/termsOfService.html" width="700" height="1000"></iframe>
		<br><br>
		<div class="btn"style="text-align: center">
			<a class="basic_btn" href="/signup">同意する</a>
			<a class="basic_btn" href="/login">同意しない</a>
		</div>
		<br><br>
		
<%-- 	<form action="signup" method="get"> --%>
<!-- 		<button class="basic_btn regist" name="agree">同意する</button> -->
<%-- 	</form> --%>
<%-- 	<form:form action="home" method="post"> --%>
<!-- 		<button class="basic_btn regist" name="register">同意しない</button> -->
<%-- 	</form:form> --%>

	</div>
</body>
</html>
<!-- <script src="./js/commons.js"></script> -->
