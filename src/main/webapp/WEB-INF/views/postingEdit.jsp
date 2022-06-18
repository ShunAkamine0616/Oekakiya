<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>画像編集画面</title>
<link href="css.css" rel="stylesheet">
</head>
<div class="container">
<body style="background-color:rgb(255, 245, 233);">
<div class="item">

    <dir class="background-img"></dir>
    <div class="button">
        <button>編集</button>
        <button>消去</button>
        <button>編集取消</button>
    </div>
</div>

<div class="item">
<div class="container2">
    <form action="" class="form">
        <div class="item2">
            <label>タイトル</label>
            <input type="text" path="title" />
        </div>
        <div class="item2">
            <label>カテゴリ</label>
            <select path="categoryid">
                <option value="1">かっこいい</option>
                <option value="2">かわいい</option>
                <option value="3">きれい</option>
                <option value="4">車</option>
                <option value="5">人物</option>
                <option value="6">その他</option>
            </select>
        </div>
        <div class="item2">
            <label>コメント</label>
            <textarea path="description"></textarea>
        </div>
    </form>
</div>
</div>
</body>
</html>