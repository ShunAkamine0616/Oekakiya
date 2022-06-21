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
        
            <form action="categoryEdit">
                <div class="categoryedit">
                   
                    
            <c:forEach var="category" items="${category}">
            <div class="item">
		            <input type="text" value="${category.getCategoryName()}" name ="${category.getId()}">
                    <button type="button"class="">✏</button>
                    <button type="submit" value="${category.getId()}" name="id" class="">🔄</button>
                    <button type="button" class="">🚮</button>
            </div>
			</c:forEach>
                    
                </div>
            </form>
  
    </div>
    </div>
</div>    
</body>
</html>