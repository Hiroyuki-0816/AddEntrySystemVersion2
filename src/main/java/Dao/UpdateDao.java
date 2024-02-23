package Dao;

import java.sql.*;
import java.util.Objects;

import Bean.InsertBean;

public class UpdateDao {
	/* �f�[�^�x�[�X��URL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DB�ɃA�N�Z�X���郆�[�U */
	private final String user = "root";
	/* �p�X���[�h */
	private final String pass = "password";
	/* ������DB�ڑ���� */
	Connection con = null;

	public void update(InsertBean ib) {
		/* JDBC�h���C�o�̓ǂݍ��� */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			/* DB�ڑ� */
			con = DriverManager.getConnection(url, user, pass);

			/* SQL�� */
			String sql = "UPDATE t_address.t_address SET name = ?, age = ?, sex = ?, job = ?, tell = ?, zip = ?, address = ?, addressdetail = ? WHERE id = ?";

			// �t�H�[������擾�����o�^���*/
			String id = ib.getId();
			String name = ib.getName();
			String age = ib.getAge();
			String sex = ib.getSex();
			String job = ib.getJob();
			String tell = ib.getTell();
			String zip = ib.getZip();
			String address = ib.getAddress();
			String addressdetail = ib.getAddressDetail();

			/* SQL����DB�֑��M */
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setString(2, age);

			if (sex.equals("male")) {
				ps.setString(3, "�j");
			} else if (sex.equals("female")) {
				ps.setString(3, "��");
			}

			switch (job) {
			case "0":
				ps.setString(4, null);
				break;
			case "1":
				ps.setString(4, "01");
				break;
			case "2":
				ps.setString(4, "02");
				break;
			case "3":
				ps.setString(4, "03");
				break;
			case "4":
				ps.setString(4, "04");
				break;
			case "5":
				ps.setString(4, "05");
				break;
			case "6":
				ps.setString(4, "06");
				break;
			case "7":
				ps.setString(4, "07");
				break;
			case "8":
				ps.setString(4, "08");
				break;
			case "9":
				ps.setString(4, "09");
				break;
			}

			ps.setString(5, tell);
			ps.setString(6, zip);
			ps.setString(7, address);

			if (!addressdetail.isEmpty()) {
				ps.setString(8, addressdetail);
			} else {
				ps.setString(8, null);
			}

			ps.setString(9, id);

			// �X�V�������s
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB�ڑ�������
			if (!Objects.isNull(con)) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
