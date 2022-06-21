<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ管理画面</title>
<link href="css/categoryMg.css" rel="stylesheet">
</head>
<body style="background-color:rgb(255, 245, 233);">
<div class="category">
    <div class="container">
    <div class="item">
    <form action="">
        <input type="text">
        <button type="submit" class="">＋</button>
    </form>
    </div>
    <div class="margin">
        <div class="item">
            <form action="">
                <div class="categoryedit">
                   
                    
            <c:forEach var="category" items="${category}">
		            <input type="text" value="${category.getCategoryName()}">
                    <button type="submit" class="">✏</button>
                    <button type="submit" class="">🔄</button>
                    <button type="submit" class="">🚮</button>
			</c:forEach>
                    
                </div>
            </form>
        </div>
    </div>
    </div>
</div>    
</body>
</html>