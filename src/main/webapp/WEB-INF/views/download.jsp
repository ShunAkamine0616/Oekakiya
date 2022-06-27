<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/commons.css" rel="stylesheet">
<link href="css/download.css" rel="stylesheet">
<link href="css/postingEdit.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.7.1/css/lightbox.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.7.1/js/lightbox.min.js" type="text/javascript"></script>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>${fn:escapeXml(image.imageTitle)}|おえかきや|画像詳細</title>


</head>



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
							class="image_circle" src="${fn:escapeXml(user.iconPath)}">
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
<div class="Dlcenter">
	<div class="container">
		<div class="item">
			<div class="leftitem">
				<div class="">
					<div class="box">
						<img src="${fn:escapeXml(image.base64)}">
					</div>
				</div>

				<!-- 				ログインしてないと、いいね数：〇で表示される -->
				<c:if test="${empty user}">
					<span>いいね数： </span>
					${fn:escapeXml(favoritecount)}
						<span>ダウンロード数：</span> ${fn:escapeXml(downloadcount) }
					

					<a href="${fn:escapeXml(image.base64)}"
						download="${fn:escapeXml(image.imageTitle)}${fn:escapeXml(image.extention)}">
						<button class="light_blue_btn" id="download_btn" onclick="showBox()">ダウンロード</button>
					</a>
					<br>

				</c:if>
			</div>



			<div class="leftitem">
				<c:if test="${not empty user}">
					<span class="iine-box"> <!--いいねしてないとき --> <img
						src="./images/blackHurt.png" id="nonHurt" class="hidden" title="いいね！"
						width=20% height=20%> <!-- いいねしてるとき --> <img
						src="./images/hurt.png" id="yesHurt" class="hidden" width=20%
						height=20%> <span class="iine"> <span
							id="favoriteNum">${fn:escapeXml(favoritecount)}</span>
					</span>
					</span>
					<span>ダウンロード数：</span>
					<span id="downloadNum">${fn:escapeXml(downloadcount) }</span>
					<br>
					<c:if test="${user.role == 1}">
						<button type="button" class="delete_btn" onclick="openModal()">削除</button>
						<div id="modal">
							<p class="modal_message">削除しますか？</p>
							<div class="btns">
								<button type="button" onclick="location.href='/adminsDelete'"
									class="basic_btn">削除</button>
								<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
							</div>
						</div>
					</c:if>
					<a href="${fn:escapeXml(image.base64)}"
						download="${fn:escapeXml(image.imageTitle)}${fn:escapeXml(image.extention)}">
						<button class="light_blue_btn" id="download_btn" onclick="showBox()">ダウンロード</button>
					</a>
				</c:if>
			</div>
		</div>



		<div class="rightitem">
			<label class="postuser">投稿者</label>
			<div class="postusericon">
				<a href="./other?id=${fn:escapeXml(imageUser.id)}"> <img
					src="${fn:escapeXml(imageUser.iconPath)}" class="image_circle">
				</a>${fn:escapeXml(imageUser.name)}
			</div>
			<div class="item">
				<div class="rightitem">
					<div class="title">
						<label>タイトル</label> <input type="text"
							value="${fn:escapeXml(image.imageTitle)}" disabled />
					</div>



					<div class="category">
						<label>カテゴリ</label> <input type="text"
							value="${fn:escapeXml(categoryName.categoryName)}" disabled />
					</div>
					<div class="comment">
						<label>コメント</label>
						<textarea disabled>${fn:escapeXml(image.comment)} </textarea>
					</div>

				</div>
			</div>
		</div>

	</div>
</div>
<div id="fadeLayer"></div>
<script>
  
var timerId;


function showBox() {
timerId = setTimeout( closeBox , 5000 ); 
document.getElementById("download_btn").disabled = true;
document.getElementById("download_btn").style.backgroundColor = "#8aa3b9";

}

function closeBox() {
clearTimeout( timerId );
document.getElementById("download_btn").disabled = false;
document.getElementById("download_btn").style.backgroundColor = "#8ED0FF";
}
  </script>
</body>
</html>
<script src="./js/commons.js">
	
</script>
<script src="./js/download.js">
	
</script>
<script src="./js/favorite.js">
	
</script>
