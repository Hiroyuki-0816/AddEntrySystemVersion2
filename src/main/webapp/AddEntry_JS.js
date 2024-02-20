function Confirm() {
	const result = window.confirm('登録しますか？');
	if (!result) {

		//キャンセルが押下されたら何もしない
		return false;
	} else {
		//OKが押下されたら登録処理を実行
		location.href = "/AddEntrySystemVersion2/Insert";
	}
}

function Delete() {
	const result = window.confirm('削除します。よろしいですか？');
	if (!result) {

		//キャンセルが押下されたら何もしない
		return false;
	} else {
		//OKが押下されたら削除処理を実行
		location.href = "/AddEntrySystemVersion2/Search";
	}
}

function Close() {
	const result = window.confirm('終了しますか？');
	if (!result) {

		//キャンセルが押下されたら何もしない
		return false;
	} else {
	//ウィンドウを閉じる
	window.open('about:blank', '_self').close();
	}
}
