<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>おえかきや|マイページ</title>
	<link href="css/commons.css" rel="stylesheet">
	<!--link href="css/commonsG.css" rel="stylesheet"-->
	<link href="css/home.css" rel="stylesheet">
	<link href="css/mypage.css" rel="stylesheet">
	<link
		href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
		rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	<link href="dist/snackbar.min.css" type="text/css" rel="stylesheet">
	</head>
	<header>
			<div class="header">
				<h1>
					<a href="./home" class="page-title gaming">おえかきや</a>
				</h1>
				
				<div class="btn-wrap">
					<c:choose>
						<c:when test="${empty user}">
							ゲスト
							<button type="button" onclick="location.href='login'"
							class="login_btn">ログイン</button>
						</c:when>
						<c:when test="${not empty user}">
							<label>
								<a href="./mypage"> <img id="iconAdd"
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
    
	<body>
<div class="flexbox">
	  <div class="item">
	  <div class="flexbox2">
	    <div class="item2">

			<div  class="site_logo">
				<h1>
					${ fn:escapeXml(user.getName()) }
				</h1>
				${ fn:escapeXml(user.getAccountId()) }<br>
				${ fn:escapeXml(user.getMail()) }<br>
			</div>
			</div>
			<div class="item2">
				<img class="icon" src="${ user.getIconPath() }">
				<br>
			</div>
			<div class="item2">
			 <div class="flexbox3">
				<div class="btns">
					<div class="item3"><a class="basic_btn regist" href="upload1">投稿</a></div>
					<div class="item3"><a class="basic_btn regist" href="./inputEditMyPage">プロフィール編集</a></div>
				</div>
			 </div>	
			</div>
			<div class="item2">
		　　　　<p>フォロワー：${fn:escapeXml(followCnt)}人</p>
            	<textarea readonly>${fn:escapeXml(user.getIntroduction())}</textarea>
			</div>

		</div>
	    </div>
			<div class="item">
				<div class="imagebox">
					<div class="myimage">
						<div class="container">
							<c:forEach var="image" items="${imageList}">
								<div style="margin: 30px;margin-bottom: 30px;">
								<div class="box">
									<a href="detailmyapage?id=${ image.getId() }"> 
										<img src=${ image.getImagePath() } class="post">
									</a>
									<p>${ fn:escapeXml(image.getImageTitle()) }</p>
								</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="myimage">
						<div class="container">
							<c:forEach var="imagefav" items="${imageFavList}">
								<div style="margin: 30px;margin-bottom: 30px;">
								<div class="box">
									<a href="detailmyapage?id=${ imagefav.getId() }"> 
										<img src=${ imagefav.getImagePath() } class="post">
									</a>
									<p>${ fn:escapeXml(imagefav.getImageTitle()) }</p>
								</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="myimage">
						<div class="container">
							<c:forEach var="imageDl" items="${imageDlList}">
								<div style="margin: 30px;margin-bottom: 30px;">
								<div class="box">
									<a href="detailmyapage?id=${ imageDl.getId() }"> 
										<img src=${ imageDl.getImagePath() } class="post">
									</a>
									<p>${ fn:escapeXml(imageDl.getImageTitle()) }</p>
								</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="myimage">
						<div class="container">
							<c:forEach var="fuser" items="${followUser}">
								<div style="margin: 30px;margin-bottom: 30px;">
									<div class="box">
										<label>
											<a href="/other?id=${ fuser.id }"> <img id="iconAdd"
												class="image_circle" src="${fn:escapeXml(fuser.iconPath)}">
											</a> ${fn:escapeXml(fuser.name)}
										</label>	
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
		        </div>
	        </div>
		</div>
	<input type ="hidden" value="${edit}" id="editFlag"></input>
	<input type ="hidden" value="${delete}" id="deleteFlag"></input>
	<script src="./dist/snackbar.min.js"></script>
	<script>
	if(document.getElementById("editFlag").value === "1") {
		Snackbar.show({pos: 'bottom-center', actionText: '閉じる', actionTextColor: '#00ff00', text: '編集完了しました。'});
	}
	if(document.getElementById("deleteFlag").value === "1") {
		Snackbar.show({pos: 'bottom-center', actionText: '閉じる', actionTextColor: '#ff0000', text: '削除しました。'});
	}
	</script>
	</body>
</html>

<script src="./js/commons.js"></script>
<script src="./js/roleChange.js">
//(´・ω・`)
</script>