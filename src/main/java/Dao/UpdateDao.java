package Dao;

import java.sql.*;

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
			}
			if (sex.equals("female")) {
				ps.setString(3, "��");
			}

			if (job.equals("0")) {
				ps.setString(4, "00");
			}
			if (job.equals("1")) {
				ps.setString(4, "01");
			}
			if (job.equals("2")) {
				ps.setString(4, "02");
			}
			if (job.equals("3")) {
				ps.setString(4, "03");
			}
			if (job.equals("4")) {
				ps.setString(4, "04");
			}
			if (job.equals("5")) {
				ps.setString(4, "05");
			}
			if (job.equals("6")) {
				ps.setString(4, "06");
			}
			if (job.equals("7")) {
				ps.setString(4, "07");
			}
			if (job.equals("8")) {
				ps.setString(4, "08");
			}
			if (job.equals("9")) {
				ps.setString(4, "09");
			}

			ps.setString(5, tell);
			ps.setString(6, zip);
			ps.setString(7, address);

			if (addressdetail != "") {
				ps.setString(8, addressdetail);
			} else {
				ps.setString(8, "");
			}

			ps.setString(9, id);

			// �X�V�������s
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB�ڑ�������
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
