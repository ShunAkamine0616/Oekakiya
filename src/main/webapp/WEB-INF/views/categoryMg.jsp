<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>おえかきや｜カテゴリ管理画面</title>
<link href="css/categoryMg.css" rel="stylesheet">
<link href="css/commons.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="dist/snackbar.min.css" />
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
						</a> ${fn:escapeXml(user.name)}

						</label>
						<button type="button" onclick="location.href='logout'"
							class="logout_btn">ログアウト</button>

					</c:when>

				</c:choose>
			</div>
		</div>
		<hr>
	</header>
<input type ="hidden" value="${delete}" id="deleteFlag"></input>
<h1>カテゴリ管理</h1>
<div class="category">
    <div class="container">
    <div class="item">
    <form action="categoryInsert" method="post">
    <div class="container2">
       <div class="item2"><input type="text"name="name" pattern="[^<^>]+" style="height: 25px;"></div>
        <div class="item2"><button type="submit" class="light_blue_btn2"><div class="pulus"><img src="images/puls.png"></div></button></div>
    </div>
    </form>
    </div>
    <div class="margin">
            <form action="categoryEdit"  method="post">
                <div class="categoryedit">
                   <div class="item">
                   <div class="msg">  <p>${msg}</p></div>
                   <button type="button" onclick="categoryopenModal()" id="submitButton"><div class="rogo"><img src="images/dust.png"></div></button> 
                    </div>
            <c:forEach var="category" items="${category}">
            <div class="item">
                    <input type="checkbox" value="${category.getId()}"name ="deleteId">
		            <input type="text" value="${fn:escapeXml(category.getCategoryName())}" pattern="[^<^>]+" name ="${category.getId()}" id="${category.getId()}" readOnly>
                    <button type="button" onclick="edit(${category.getId()})" class=""><div class="rogo"><img src="images/pen.png"></div></button>
                    <button type="submit" value="${category.getId()}" name="editId" class=""><div class="rogo"><img src="images/Road.png"></div></button>
             <!--   <button type="button" onclick="openModal()" value="${category.getId()}"name ="deleteId">🚮</button>--> 
            </div>
			</c:forEach>
			
                </div>
                
               <div id="modal">
					<p class="modal_message">削除しますか？</p>
					<div class="btns">
						<button type="submit" name="delete" class="basic_btn" id="bar">削除</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
		     </form>
    </div>
    </div>
</div> 
	<div id="fadeLayer"></div>   
	<script src="./dist/snackbar.min.js"></script>
	<script src="./js/commons.js"></script>
	<script>
	if(document.getElementById("deleteFlag").value === "1") {
		Snackbar.show({actionText: '閉じる',text: 'カテゴリを削除しました。'});
	//	document.getElementById("deleteFlag").value == "2";
	}
	</script>
</body>
</html>


