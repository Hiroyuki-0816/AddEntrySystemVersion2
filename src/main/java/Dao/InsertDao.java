package Dao;

import java.sql.*;
import java.util.Objects;

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
			} else if (sex.equals("female")) {
				ps.setString(4, "女");
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

			// 登録処理実行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB接続を解除
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
