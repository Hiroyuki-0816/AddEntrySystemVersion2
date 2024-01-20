package Validation;

import java.util.ArrayList;

import Bean.ArgumentBean;

public class SearchValidation {

	/* エラーメッセージ一覧 */
	private String msg = "に誤りがあります。入力した文字数が多すぎます。";
	private String msg2 = "に誤りがあります。入力した文字の型が違います。";
	private String msg3 = "の範囲指定に誤りがあります";

	/* 入力された検索条件のエラーチェックを実行するメソッド */
	public ArrayList<String> errorCheckS(ArgumentBean ab) {

		/* エラーメッセージを格納するリスト */
		ArrayList<String> errorMessages = new ArrayList<String>();

		/* フォームから取得した検索条件 */
		String idfrom = ab.getIdfrom();
		String idto = ab.getIdto();
		String name = ab.getName();
		String agefrom = ab.getAgefrom();
		String ageto = ab.getAgeto();
		String tell = ab.getTell();
		String zip = ab.getZip();
		String address = ab.getAddress();
		String addressdetail = ab.getAddressdetail();

		if (idfrom.length() > 8) {
			errorMessages.add("登録IDFROM" + msg);
		} else if (idfrom != "" && !idfrom.matches("^[0-9]+$")) {
			errorMessages.add("登録IDFROM" + msg2);
		}

		if (idto.length() > 8) {
			errorMessages.add("登録IDTO" + msg);
		} else if (idto != "" && !idto.matches("^[0-9]+$")) {
			errorMessages.add("登録IDTO" + msg2);
		}

		if ((idfrom != "" && idto != "") && (idfrom.matches("^[0-9]+$") && idto.matches("^[0-9]+$"))
				&& Integer.parseInt(idfrom) > Integer.parseInt(idto)) {
			errorMessages.add("登録ID" + msg3);
		}

		if (name.length() > 20) {
			errorMessages.add("氏名" + msg);
		}

		if (agefrom.length() > 3) {
			errorMessages.add("年齢FROM" + msg);
		} else if (agefrom != "" && !agefrom.matches("^[0-9]+$")) {
			errorMessages.add("年齢FROM" + msg2);
		}

		if (ageto.length() > 3) {
			errorMessages.add("年齢TO" + msg);
		} else if (ageto != "" && !ageto.matches("^[0-9]+$")) {
			errorMessages.add("年齢TO" + msg2);
		}

		if ((agefrom != "" && ageto != "") && (agefrom.matches("^[0-9]+$") && ageto.matches("^[0-9]+$"))
				&& Integer.parseInt(agefrom) > Integer.parseInt(ageto)) {
			errorMessages.add("年齢" + msg3);
		}

		if (tell.length() > 13) {
			errorMessages.add("電話番号" + msg);
		} else if (tell != "" && !tell.matches("^[-0-9]+$")) {
			errorMessages.add("電話番号" + msg2);
		}

		if (zip.length() > 8) {
			errorMessages.add("郵便番号" + msg);
		} else if (zip != "" && !zip.matches("^[-0-9]+$")) {
			errorMessages.add("郵便番号" + msg2);
		}

		if (address.length() > 20) {
			errorMessages.add("住所" + msg);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("番地" + msg);
		}
		return errorMessages;
	}

}
