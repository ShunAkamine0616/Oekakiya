//ボタン押したとき　追加
var follow = document.getElementById("follow");
var lift = document.getElementById("lift");

// ページ読み込み時に実行したい処理
document.addEventListener('DOMContentLoaded', function(){
fetch("checkFollow")
.then(response => response.text())
.then(data => {
	console.log(data);
	//1がフォローしている
	if(data == 1) {
		lift.className = "basic_btn regist";
		follow.className = "basic_btn regist hidden";
	} else {
		lift.className = "basic_btn regist hidden";
		follow.className = "basic_btn regist";
	}
	
})});

follow.addEventListener('click',event =>{
	fetch("insertFollow")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("followNum").textContent = data;
	var button = document.get
	follow.className = "basic_btn regist hidden";
	lift.className = "basic_btn regist";
})});


//再度押したとき　削除
lift.addEventListener('click',event =>{
	fetch("liftFollow")
.then(response => response.text())
.then(data => {
	console.log(data);
	document.getElementById("followNum").textContent = data;
	follow.className = "basic_btn regist";
	lift.className = "basic_btn regist hidden";
})});