//いいね押したとき　追加
var nonImg = document.getElementById("nonHurt");
var yesImg = document.getElementById("yesHurt");

// ページ読み込み時に実行したい処理
document.addEventListener('DOMContentLoaded', function(){
fetch("checkFavorite")
.then(response => response.text())
.then(data => {
	console.log(data);
	if(data == 1) {
		nonImg.className = "hidden";
		yesImg.className = null;
	} else {
		nonImg.className = null;
		yesImg.className = "hidden";
	}
	
})});

nonImg.addEventListener('click',event =>{
	fetch("FavoriteCount")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("favoriteNum").textContent = data;
	nonImg.className = "hidden";
	yesImg.className = null;
})});


//いいね押したとき　削除
yesImg.addEventListener('click',event =>{
	fetch("FavoriteDelete")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("favoriteNum").textContent = data;
	nonImg.className = null;
	yesImg.className = "hidden";
})});