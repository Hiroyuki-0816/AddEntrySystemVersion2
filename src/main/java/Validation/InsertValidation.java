package Validation;

import java.util.ArrayList;

import Bean.InsertBean;

public class InsertValidation {

	/* �G���[���b�Z�[�W�ꗗ */
	String msg = "�͕K�����͂��Ă��������B";
	String msg2 = "�Ɍ�肪����܂��B���͂������������������܂��B";
	String msg3 = "�Ɍ�肪����܂��B���͂��������̌^���Ⴂ�܂��B";

	/* ���͒l�̃G���[�`�F�b�N�����s���郁�\�b�h */
	public ArrayList<String> errorCheckI(InsertBean ib) {

		/* �G���[���b�Z�[�W���i�[���郊�X�g */
		ArrayList<String> errorMessages = new ArrayList<String>();

		/* �t�H�[������擾�����o�^��� */
		String id = ib.getId();
		String name = ib.getName();
		String age = ib.getAge();
		String tell = ib.getTell();
		String zip = ib.getZip();
		String address = ib.getAddress();
		String addressdetail = ib.getAddressDetail();

		if (id.isEmpty()) {
			errorMessages.add("�o�^ID" + msg);
		} else if (id.length() > 8) {
			errorMessages.add("�o�^ID" + msg2);
		} else if (!id.matches("^[0-9]+$")) {
			errorMessages.add("�o�^ID" + msg3);
		}

		if (name.isEmpty()) {
			errorMessages.add("����" + msg);
		} else if (name.length() > 20) {
			errorMessages.add("����" + msg2);
		}

		if (age.isEmpty()) {
			errorMessages.add("�N��" + msg);
		} else if (age.length() > 3) {
			errorMessages.add("�N��" + msg2);
		} else if (!age.matches("^[0-9]+$")) {
			errorMessages.add("�N��" + msg3);
		}

		if (tell.isEmpty()) {
			errorMessages.add("�d�b�ԍ�" + msg);
		} else if (tell.length() > 13) {
			errorMessages.add("�d�b�ԍ�" + msg2);
		} else if (!tell.matches("^[-0-9]+$")) {
			errorMessages.add("�d�b�ԍ�" + msg3);
		} else if (tell.startsWith("-") || tell.endsWith("-")) {
			errorMessages.add("�n�C�t��'-'�ŊJ�n�A�܂��͏I�����镶����̓o�^�͂ł��܂���B");
		}

		if (zip.isEmpty()) {
			errorMessages.add("�X�֔ԍ�" + msg);
		} else if (zip.length() > 8) {
			errorMessages.add("�X�֔ԍ�" + msg2);
		} else if (!zip.matches("^[-0-9]+$")) {
			errorMessages.add("�X�֔ԍ�" + msg3);
		} else if (!zip.matches("[0-9]{3}-[0-9]{4}")) {
			errorMessages.add("�X�֔ԍ��͏����F�u999-9999�v�łȂ���Γo�^�ł��܂���B");
		}

		if (address.isEmpty()) {
			errorMessages.add("�Z��" + msg);
		} else if (address.length() > 20) {
			errorMessages.add("�Z��" + msg2);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("�Ԓn" + msg2);
		}
		return errorMessages;

	}
}
