function Confirm() {
	const result = window.confirm('登録しますか？');
	if (!result) {

		//キャンセルが押下されたら何もしない
		return false;
	} else {
		//OKが押下されたら登録・更新処理を実行
		location.href = "/AddEntrySystemVersion2/Insert";
	}
}

function Close(){
	const closeBtn = document.getElementById('close');

closeBtn.addEventListener('click', function () {
	
  window.open('', '_self').close();

});
}
