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
<link rel="stylesheet" href="css/header.css">
<title>Insert title here</title>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>おえかきや</title>
    
    <script src="https://code.iconify.design/1/1.0.6/iconify.min.js"></script>
</head>
<body style="background-color:rgb(255, 245, 233);">
<div class="category">
    <div class="container">
    <div class="item">
<!-- <header> -->
<!--       <div class="header"> -->
<!--       <h1><a href="./home" class="page-title">おえかきや</a></h1> -->

<!--       <div class="btn"> -->
<%--         <c:choose> --%>
<%--           <c:when test="${empty user}"> --%>
    
<!--            <input type="button" onclick="location.href='login'" value="ログイン"class="login_btn"> -->
        
<!--             ゲスト -->
<!--             {$image.getIconPath} -->
<%--           </c:when> --%>
<%--          <c:when test="${user.role==1}"> --%>
<%--          </c:when> --%>
<%--           <c:otherwise> --%>
           

<!--               <div class="btn"> -->
<!--                 <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled"></span> -->
<!--               </div> -->

<!--               <div class="tooltip display-none"> -->
              
<!--                  <a href="./mypage" class="to-mypage item"> -->
<!--                     <span class="iconify" data-inline="false" data-icon="carbon:user-avatar-filled"></span> -->
<!--                     マイページ -->
<!--                   </a> -->
<!--                 <button type="submit" class="logout item"> -->
<!--                     <span class="iconify" data-inline="false" data-icon="carbon:logout"></span> -->
<!--                     ログアウト -->
<!--                   </button> -->
<!--               </div> -->
<%--           </c:otherwise> --%>
<%--         </c:choose> --%>
<!--       </div> -->
<!--     </div> -->
<!--     </header> -->



<header>
      <div class="header">
      <h1><a href="./home" class="page-title">おえかきや</a></h1>
     
      <div class="btn-wrap">
        <c:choose>
          <c:when test="${empty user}">
        ゲスト
            <button type="button" onclick="location.href='login'" class="login_btn">ログイン</button>
          </c:when>
          <c:when test="${not empty user}">
         <${user.userIcon}>
          <button type="button" onclick="location.href='login'"  class="logout_btn">ログアウト</button>
       
          </c:when>
          
        </c:choose>
      </div>
    </div>
    </header>
    <br>
    <br>
<!--     <hr width=auto class ="header_line"> -->
    
      </div>
    </div>
</div>
<c:if test="${user.role == 2}">
<button class="light_blue_btn">
  <a href="${downloadImg.imagePath}" download="${downloadImg.imageTitle}" >削除</a>
  </button>
  </c:if>
<label>

<button class="light_blue_btn">
  <a href="${downloadImg.imagePath}" download="${downloadImg.imageTitle}" >ダウンロード</a>
  </button>
  </label>
  
</body>
</html>