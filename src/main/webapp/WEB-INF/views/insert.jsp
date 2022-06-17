<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<h2 >
			<a href="menu.html">店舗登録</a>
		</h2>
		<div class="user">
			<p class="user_name">${User.getName()}</p>
			<a href="/logout" class="button">ログアウト</a>
		</div>
	</div>

	<hr>

	<div class="insert">
		<div class="discription">
			<p>
				登録情報を入力してください（<span class="required"></span>は必須です）
			</p>
		</div>

		<div class="form_body">
			<p class="error">${msg}</p>

			<form:form action="insert" method="post" modelAttribute="insert">
				<fieldset class="label-130">
					<div>
						<label class="required">店名</label>
						<form:input type="text" path="shopName" class="base-text" />
						<form:errors path="shopName" cssStyle="color: red" />

					</div>
					<div>
						<label class="required">電話番号</label>
						<form:input type="text" path="telnumber" class="base-text" />
						<form:errors path="telnumber" cssStyle="color: red" />
					</div>
					<div>
						<label class="required">店舗パスワード</label>
						<form:input type="password" path="pass" class="base-text" />
						<form:errors path="pass" cssStyle="color: red" />
					</div>
					<div class="select_block">
						<label class="required">ジャンル</label>
						<form:select path="categoryid" class="base-text">
							<option value="1">沖縄食堂</option>
							<option value="2">沖縄居酒場</option>
							<option value="3">ステーキ</option>
							<option value="4">タコス・タコライス</option>
							<option value="5">沖縄そば</option>
							<option value="6">沖縄スイーツ</option>
						</form:select>
						<form:errors path="categoryid" cssStyle="color: red" />
					</div>
					<div class="select_block">
						<label class="required">エリア</label>
						<form:select path="areaid" class="base-text">
							<option value="1">北部</option>
							<option value="2">中部</option>
							<option value="3">南部</option>
						</form:select>
						<form:errors path="areaid" cssStyle="color: red" />
					</div>
					<div>
						<label>店舗紹介文</label>
						<form:textarea path="description" class="base-text"></form:textarea>
						<form:errors path="description" cssStyle="color: red" />
					</div>

				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">登録</button>
					<input type="button" onclick="location.href='back'" value="戻る"
						class="cancel_btn">
				</div>

				<div id="modal">
					<p class="modal_message">登録しますか？</p>
					<div class="btns">
						<button type="submit" class="basic_btn">登録</button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>

				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js">
	
</script>