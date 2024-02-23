package Dao;

import java.sql.*;
import java.util.Objects;

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
			} else if (sex.equals("female")) {
				ps.setString(4, "��");
			}

			switch (job) {
			case "0":
				ps.setString(5, null);
				break;
			case "1":
				ps.setString(5, "01");
				break;
			case "2":
				ps.setString(5, "02");
				break;
			case "3":
				ps.setString(5, "03");
				break;
			case "4":
				ps.setString(5, "04");
				break;
			case "5":
				ps.setString(5, "05");
				break;
			case "6":
				ps.setString(5, "06");
				break;
			case "7":
				ps.setString(5, "07");
				break;
			case "8":
				ps.setString(5, "08");
				break;
			case "9":
				ps.setString(5, "09");
				break;
			}

			ps.setString(6, tell);
			ps.setString(7, zip);
			ps.setString(8, address);

			if (!addressdetail.isEmpty()) {
				ps.setString(9, addressdetail);
			} else {
				ps.setString(9, null);
			}

			// �o�^�������s
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
