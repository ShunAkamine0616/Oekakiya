<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>画像編集画面</title>
<link href="css/postingEdit.css" rel="stylesheet">
<link href="css/commons.css" rel="stylesheet">
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
						<button type="button" onclick="location.href='logout'"
							class="logout_btn">ログアウト</button>

					</c:when>

				</c:choose>
			</div>
		</div>
		<hr>
	</header>

<form:form action="edit" class="form" method="post" modelAttribute="postingEdit">
 <div class="container">
    <div class="item">
    	<div class="leftitem">
           <div class=""><div class="box"><img src=${ images.getImagePath() }></div></div>
             <p>💛${count.getFavorite()} ⇩${count.getDownload()}</p>
				<form:button type="submit" class="light_blue_btn">編集</form:button>
				<button type="button" onclick="openModal()"class="delete_btn">消去</button>
				<div id="modal">
					<p class="modal_message">消去しますか？</p>
					<div class="btns">
						<button type="button" onclick="location.href='/delete'"class="basic_btn">削除</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
				<button type="button" onclick="location.href='./mypegeBack'" class="gray_btn">編集取消</button>
		</div>
	</div>
 <div class="item">
 	<div class="rightitem">
        <div class="title">
            <label>タイトル</label>
            <form:input type="text" value="${images.getImageTitle()}" path="title"/><form:errors path="title" cssStyle="color: red" />
            
        </div>
        <div class="category">
            <label>カテゴリ</label>
            <form:select path="categoryId">
            <form:options items="${category}" itemLabel="categoryName" itemValue="id"/>
            </form:select>
			
        </div>
        <div class="comment">
            <label>コメント</label>
            <form:textarea path="comment"></form:textarea>
        </div>
   </div>
</div>

 </div>
</form:form>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js">
	
</script>