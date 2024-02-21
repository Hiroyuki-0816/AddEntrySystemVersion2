package Validation;

import java.util.ArrayList;

import Bean.InsertBean;

public class InsertValidation {

	/* エラーメッセージ一覧 */
	String msg = "は必ず入力してください。";
	String msg2 = "に誤りがあります。入力した文字数が多すぎます。";
	String msg3 = "に誤りがあります。入力した文字の型が違います。";

	/* 入力値のエラーチェックを実行するメソッド */
	public ArrayList<String> errorCheckI(InsertBean ib) {

		/* エラーメッセージを格納するリスト */
		ArrayList<String> errorMessages = new ArrayList<String>();

		/* フォームから取得した登録情報 */
		String id = ib.getId();
		String name = ib.getName();
		String age = ib.getAge();
		String tell = ib.getTell();
		String zip = ib.getZip();
		String address = ib.getAddress();
		String addressdetail = ib.getAddressDetail();

		if (id.isEmpty()) {
			errorMessages.add("登録ID" + msg);
		} else if (id.length() > 8) {
			errorMessages.add("登録ID" + msg2);
		} else if (!id.matches("^[0-9]+$")) {
			errorMessages.add("登録ID" + msg3);
		}

		if (name.isEmpty()) {
			errorMessages.add("氏名" + msg);
		} else if (name.length() > 20) {
			errorMessages.add("氏名" + msg2);
		}

		if (age.isEmpty()) {
			errorMessages.add("年齢" + msg);
		} else if (age.length() > 3) {
			errorMessages.add("年齢" + msg2);
		} else if (!age.matches("^[0-9]+$")) {
			errorMessages.add("年齢" + msg3);
		}

		if (tell.isEmpty()) {
			errorMessages.add("電話番号" + msg);
		} else if (tell.length() > 13) {
			errorMessages.add("電話番号" + msg2);
		} else if (!tell.matches("^[-0-9]+$")) {
			errorMessages.add("電話番号" + msg3);
		} else if (tell.startsWith("-") || tell.endsWith("-")) {
			errorMessages.add("ハイフン'-'で開始、または終了する文字列の登録はできません。");
		}

		if (zip.isEmpty()) {
			errorMessages.add("郵便番号" + msg);
		} else if (zip.length() > 8) {
			errorMessages.add("郵便番号" + msg2);
		} else if (!zip.matches("^[-0-9]+$")) {
			errorMessages.add("郵便番号" + msg3);
		} else if (!zip.matches("[0-9]{3}-[0-9]{4}")) {
			errorMessages.add("郵便番号は書式：「999-9999」でなければ登録できません。");
		}

		if (address.isEmpty()) {
			errorMessages.add("住所" + msg);
		} else if (address.length() > 20) {
			errorMessages.add("住所" + msg2);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("番地" + msg2);
		}
		return errorMessages;

	}
}
