//いいね押したとき　追加
var nonImg = document.getElementsByClassName("nonHurt");
var yesImg = document.getElementsByClassName("yesHurt");

for(let i = 0; i < nonImg.length; i++) {
	nonImg[i].addEventListener('click',event =>{
		fetch("HomeFavoriteCount?imageId="+event.target.id)
		.then(response => response.text())
		.then(data => {
			console.log(data);
			document.getElementById(event.target.id).textContent = data;
			nonImg[i].className = "nonHurt hidden";
			yesImg[i].className = "yesHurt";
		})
	});
}

//いいね押したとき　削除
for(let i = 0; i < yesImg.length; i++) {
yesImg[i].addEventListener('click',event =>{
	fetch("HomeFavoriteDelete?imageId="+event.target.id)
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById(event.target.id).textContent = data;
	nonImg.className = null;
	yesImg.className = "hidden";
})});