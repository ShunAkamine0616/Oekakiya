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
<link href="css.css" rel="stylesheet">
</head>

<body style="background-color:rgb(255, 245, 233);">
<div class="container">
<div class="item">

    <div class="background-img"></div>

</div>

<div class="item">
<div class="container2">
    <form:form action="edit" class="form" method="post" modelAttribute="postingEdit">
        <div class="item2">
            <label>タイトル</label>
            <form:input type="text" path="title"/>
        </div>
        <div class="item2">
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
        <div class="item2">
            <label>コメント</label>
            <form:textarea path="comment"></form:textarea>
        </div>
<div class="button">
	<form:button name="param1">編集</form:button>
	<form:button name="param2">消去</form:button>
	<form:button name="param3">編集取消</form:button>
</div>
    </form:form>
</div>
</div>
</div>
</body>
</html>