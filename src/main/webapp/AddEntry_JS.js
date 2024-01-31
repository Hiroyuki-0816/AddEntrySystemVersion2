function clearButtonClickE() {
	document.getElementById("id").value = "";
	document.getElementById("name").value = "";
	document.getElementById("age").value = "";
	document.getElementById("sex").checked = true;
	document.getElementById("job").selected = true;
	document.getElementById("tell").value = "";
	document.getElementById("zip").value = "";
	document.getElementById("address").value = "";
	document.getElementById("addressdetail").value = "";
}

function clearButtonClickS() {
	document.getElementById("idfrom").value = "";
	document.getElementById("idto").value = "";
	document.getElementById("name").value = "";
	document.getElementById("agefrom").value = "";
	document.getElementById("ageto").value = "";
	document.getElementById("sex").checked = true;
	document.getElementById("job").selected = true;
	document.getElementById("tell").value = "";
	document.getElementById("zip").value = "";
	document.getElementById("address").value = "";
	document.getElementById("addressdetail").value = "";
}

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
	
  window.close();

});
}
