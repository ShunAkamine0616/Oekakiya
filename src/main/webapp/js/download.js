document.getElementById("download_btn").addEventListener('click',event =>){
	fetch("DownloadCount")
.then(response => response.text())
.then(data => {
	document.ElementById("downloadNum").textContent = data;
});




$(function(){

  $(".light_blue_btn").on('click', function(){

    $btn = $(this);

    //（１）押せなくする
    function LikesBtnDisable(){
      $btn.addClass("isDisable");
      clearInterval(statusDis);
    }
    //（２）押せるようにする
    function LikesBtnAble(){
      $btn.removeClass("isDisable");
      clearInterval(statusAble);
    }
    function LikesBtnClicked(){
      statusDis  = setInterval(LikesBtnDisable , 1); //ボタンを押した直後に（１）を呼び出し
      statusAble = setInterval(LikesBtnAble , 2000); //ボタンを押して３秒後に（２）を呼び出し
    }
    LikesBtnClicked();
  });