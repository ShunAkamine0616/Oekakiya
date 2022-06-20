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
</head>

<body style="background-color:#F5F0E9;">


<form:form action="edit" class="form" method="post" modelAttribute="postingEdit">
 <div class="container">
    <div class="item">
        <div class="background-img"></div>
    		<div class="">
				<form:button type="submit">編集</form:button>
				<button type="button" onclick="openModal()">消去</button>
				<div id="modal">
					<p class="modal_message">消去しますか？</p>
					<div class="btns">
						<button type="button" onclick="location.href='./delete'"class="basic_btn">削除</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
				<button type="button" onclick="location.href='./back'">編集取消</button>
			</div>
	</div>
 <div class="item">
        <div class="title">
            <label>タイトル</label>
            <form:input type="text" value="${image.getImageTitle()}" path="title"/>
            
        </div>
        <div class="category">
            <label>カテゴリ</label>
            <form:select path="categoryid">
                <option value="1">かっこいい</option>
                <option value="2">かわいい</option>
                <option value="3">きれい</option>
                <option value="4">車</option>
                <option value="5">人物</option>
                <option value="6">その他</option>
            </form:select>
        </div>
        <div class="comment">
            <label>コメント</label>
            <form:textarea path="comment"></form:textarea>
        </div>
</div>

 </div>
</form:form>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js">
	
</script>