//いいね押したとき　追加
document.getElementById("iineBtn").addEventListener('click',event =>{
	fetch("FavoriteCount")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("favoriteNum").textContent = data;
})});


//いいね押したとき　削除
document.getElementById("onIineBtn").addEventListener('click',event =>{
	fetch("FavoriteDelete")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("favoriteNum").textContent = data;
})});