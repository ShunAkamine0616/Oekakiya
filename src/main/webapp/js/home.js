jQuery(function(){
  // チェックボックスの表示切替ボタンのクリックイベントを検知
  jQuery('.checkbox-toggle').on('click', function(){
    // チェックボックスの表示をslideToggleで切り替える
    jQuery('.checkboxes').slideToggle();
  });
});