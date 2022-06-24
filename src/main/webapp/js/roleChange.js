//ボタン押したとき　追加
var assign = document.getElementById("assign");
var deprivation = document.getElementById("deprivation");

// ページ読み込み時に実行したい処理
document.addEventListener('DOMContentLoaded', function(){
fetch("checkRole")
.then(response => response.text())
.then(data => {
	console.log(data);
	//1がフォローしている
	if(data == 1) {
		assign.className = "basic_btn regist";
		deprivation.className = "basic_btn regist hidden";
	} else {
		assign.className = "basic_btn regist hidden";
		deprivation.className = "basic_btn regist";
	}
	
})});

assign.addEventListener('click',event =>{
	fetch("assignAdmin")
.then(response => response.text())
.then(data => {
	console.log(data);
	var button = document.get
	assign.className = "basic_btn regist hidden";
	deprivation.className = "basic_btn regist";
	Snackbar.show({pos: 'bottom-center', actionText: '閉じる', actionTextColor: '#00ff00', text: '管理者権限を付与しました。'});
})});


//再度押したとき　削除
deprivation.addEventListener('click',event =>{
	fetch("deprivationAdmin")
.then(response => response.text())
.then(data => {
	console.log(data);
	assign.className = "basic_btn regist";
	deprivation.className = "basic_btn regist hidden";
	Snackbar.show({pos: 'bottom-center', actionText: '閉じる', actionTextColor: '#ff0000', text: '管理者権限を解除しました。'});
})});