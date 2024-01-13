package Dao;

import java.sql.*;

import Bean.InsertBean;

public class InsertDao {
	/* �f�[�^�x�[�X��URL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DB�ɃA�N�Z�X���郆�[�U */
	private final String user = "root";
	/* �p�X���[�h */
	private final String pass = "password";
	/* ������DB�ڑ���� */
	Connection con = null;

	public void insert(InsertBean ib) {
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
			String sql = "INSERT INTO t_address.t_address(id,name,age,sex,job,tell,zip,address,addressdetail) VALUES(?,?,?,?,?,?,?,?,?)";

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

			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, age);

			if (sex.equals("male")) {
				ps.setString(4, "�j");
			}
			if (sex.equals("female")) {
				ps.setString(4, "��");
			}

			if (job.equals("0")) {
				ps.setString(5, "");
			}
			if (job.equals("1")) {
				ps.setString(5, "01");
			}
			if (job.equals("2")) {
				ps.setString(5, "02");
			}
			if (job.equals("3")) {
				ps.setString(5, "03");
			}
			if (job.equals("4")) {
				ps.setString(5, "04");
			}
			if (job.equals("5")) {
				ps.setString(5, "05");
			}
			if (job.equals("6")) {
				ps.setString(5, "06");
			}
			if (job.equals("7")) {
				ps.setString(5, "07");
			}
			if (job.equals("8")) {
				ps.setString(5, "08");
			}
			if (job.equals("9")) {
				ps.setString(5, "09");
			}

			ps.setString(6, tell);
			ps.setString(7, zip);
			ps.setString(8, address);

			if (addressdetail != "") {
				ps.setString(9, addressdetail);
			} else {
				ps.setString(9, "");
			}

			// �o�^�������s
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
