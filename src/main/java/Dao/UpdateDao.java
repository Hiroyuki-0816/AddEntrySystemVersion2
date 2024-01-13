package Dao;

import java.sql.*;

import Bean.InsertBean;

public class UpdateDao {
	/* データベースのURL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DBにアクセスするユーザ */
	private final String user = "root";
	/* パスワード */
	private final String pass = "password";
	/* 初期のDB接続状態 */
	Connection con = null;

	public void update(InsertBean ib) {
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
			String sql = "UPDATE t_address.t_address SET name = ?, age = ?, sex = ?, job = ?, tell = ?, zip = ?, address = ?, addressdetail = ? WHERE id = ?";

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

			ps.setString(1, name);
			ps.setString(2, age);

			if (sex.equals("male")) {
				ps.setString(3, "男");
			}
			if (sex.equals("female")) {
				ps.setString(3, "女");
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

			// 更新処理実行
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
