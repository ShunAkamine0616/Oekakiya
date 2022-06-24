var nonImg = document.getElementsByClassName("nonHurt");
var yesImg = document.getElementsByClassName("yesHurt");

//いいね押したとき　追加
for(let i = 0; i < nonImg.length; i++) {
	nonImg[i].addEventListener('click',event =>{
		fetch("HomeFavoriteCount?imageId="+event.target.id)
		.then(response => response.text())
		.then(data => {
			document.getElementById(event.target.id).textContent = data;
			nonImg[i].className = "favorite_btn nonHurt hidden";
			yesImg[i].className = "favorite_btn yesHurt";
		})
	});
}

//いいね押したとき　削除
for(let i = 0; i < yesImg.length; i++) {
yesImg[i].addEventListener('click',event =>{
	fetch("HomeFavoriteDelete?imageId="+event.target.id)
	.then(response => response.text())
	.then(data => {
		document.getElementById(event.target.id).textContent = data;
		nonImg[i].className = "favorite_btn nonHurt";
		yesImg[i].className = "favorite_btn yesHurt hidden";
		})
	});
}