package Validation;

import java.util.ArrayList;

import Bean.ArgumentBean;

public class SearchValidation {

	/* �G���[���b�Z�[�W�ꗗ */
	private String msg = "�Ɍ�肪����܂��B���͂������������������܂��B";
	private String msg2 = "�Ɍ�肪����܂��B���͂��������̌^���Ⴂ�܂��B";
	private String msg3 = "�͈͎̔w��Ɍ�肪����܂�";

	/* ���͂��ꂽ���������̃G���[�`�F�b�N�����s���郁�\�b�h */
	public ArrayList<String> errorCheckS(ArgumentBean ab) {

		/* �G���[���b�Z�[�W���i�[���郊�X�g */
		ArrayList<String> errorMessages = new ArrayList<String>();

		/* �t�H�[������擾������������ */
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
			errorMessages.add("�o�^IDFROM" + msg);
		} else if (!idfrom.isEmpty() && !idfrom.matches("^[0-9]+$")) {
			errorMessages.add("�o�^IDFROM" + msg2);
		}

		if (idto.length() > 8) {
			errorMessages.add("�o�^IDTO" + msg);
		} else if (!idto.isEmpty() && !idto.matches("^[0-9]+$")) {
			errorMessages.add("�o�^IDTO" + msg2);
		}

		if ((!idfrom.isEmpty() && !idto.isEmpty()) && (idfrom.matches("^[0-9]+$") && idto.matches("^[0-9]+$"))
				&& Integer.parseInt(idfrom) > Integer.parseInt(idto)) {
			errorMessages.add("�o�^ID" + msg3);
		}

		if (name.length() > 20) {
			errorMessages.add("����" + msg);
		}

		if (agefrom.length() > 3) {
			errorMessages.add("�N��FROM" + msg);
		} else if (!agefrom.isEmpty() && !agefrom.matches("^[0-9]+$")) {
			errorMessages.add("�N��FROM" + msg2);
		}

		if (ageto.length() > 3) {
			errorMessages.add("�N��TO" + msg);
		} else if (!ageto.isEmpty() && !ageto.matches("^[0-9]+$")) {
			errorMessages.add("�N��TO" + msg2);
		}

		if ((!agefrom.isEmpty() && !ageto.isEmpty()) && (agefrom.matches("^[0-9]+$") && ageto.matches("^[0-9]+$"))
				&& Integer.parseInt(agefrom) > Integer.parseInt(ageto)) {
			errorMessages.add("�N��" + msg3);
		}

		if (tell.length() > 13) {
			errorMessages.add("�d�b�ԍ�" + msg);
		} else if (!tell.isEmpty() && !tell.matches("^[-0-9]+$")) {
			errorMessages.add("�d�b�ԍ�" + msg2);
		}

		if (zip.length() > 8) {
			errorMessages.add("�X�֔ԍ�" + msg);
		} else if (!zip.isEmpty() && !zip.matches("^[-0-9]+$")) {
			errorMessages.add("�X�֔ԍ�" + msg2);
		}

		if (address.length() > 20) {
			errorMessages.add("�Z��" + msg);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("�Ԓn" + msg);
		}
		return errorMessages;
	}

}
