//いいね押したとき　追加
var nonImg = document.getElementById("nonHurt");
var yesImg = document.getElementById("yesHurt");

nonImg.addEventListener('click',event =>{
	fetch("FavoriteCount")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("favoriteNum").textContent = data;
	var button = document.get
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