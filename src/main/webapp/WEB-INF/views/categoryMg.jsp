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
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
<h1>カテゴリ管理</h1>
<div class="category">
    <div class="container">
    <div class="item">
    <form action="categoryinsert">
        <input type="text"name="name">
        <button type="submit" class="">＋</button>
    </form>
    </div>
    <div class="margin">
            <form action="categoryEdit">
                <div class="categoryedit">
                   <div class="item">
                   <button type="button" onclick="openModal()">🚮</button> 
                    </div>
                    
            <c:forEach var="category" items="${category}">
            <div class="item">
                    <input type="checkbox" value="${category.getId()}"name ="deleteId">  
		            <input type="text" value="${category.getCategoryName()}" name ="${category.getId()}" id="${category.getId()}" readOnly>
                    <button type="button" onclick="edit(${category.getId()})" class="">✏</button>
                    <button type="submit" value="${category.getId()}" name="editId" class="">🔄</button>
             <!--   <button type="button" onclick="openModal()" value="${category.getId()}"name ="deleteId">🚮</button>--> 
            </div>
			</c:forEach>
			
                </div>
                
               <div id="modal">
					<p class="modal_message">消去しますか？</p>
					<div class="btns">
						<button type="submit" name="delete" class="basic_btn">削除</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
		     </form>
    </div>
    </div>
</div> 
	<div id="fadeLayer"></div>   
</body>
</html>
<script src="./js/commons.js">

</script>
