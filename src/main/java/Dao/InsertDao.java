package Dao;

import java.sql.*;

import Bean.InsertBean;

public class InsertDao {
	/* データベースのURL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DBにアクセスするユーザ */
	private final String user = "root";
	/* パスワード */
	private final String pass = "password";
	/* 初期のDB接続状態 */
	Connection con = null;

	public void insert(InsertBean ib) {
		/* JDBCドライバの読み込み */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			/* DB接続 */
			con = DriverManager.getConnection(url, user, pass);

			/* SQL文 */
			String sql = "INSERT INTO t_address.t_address(id,name,age,sex,job,tell,zip,address,addressdetail) VALUES(?,?,?,?,?,?,?,?,?)";

			// フォームから取得した登録情報*/
			String id = ib.getId();
			String name = ib.getName();
			String age = ib.getAge();
			String sex = ib.getSex();
			String job = ib.getJob();
			String tell = ib.getTell();
			String zip = ib.getZip();
			String address = ib.getAddress();
			String addressdetail = ib.getAddressDetail();

			/* SQL文をDBへ送信 */
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, age);

			if (sex.equals("male")) {
				ps.setString(4, "男");
			}
			if (sex.equals("female")) {
				ps.setString(4, "女");
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

			// 登録処理実行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB接続を解除
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
