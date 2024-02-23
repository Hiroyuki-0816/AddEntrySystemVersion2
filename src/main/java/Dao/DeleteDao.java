package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DeleteDao {
	/* データベースのURL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DBにアクセスするユーザ */
	private final String user = "root";
	/* パスワード */
	private final String pass = "password";
	/* 初期のDB接続状態 */
	Connection con = null;

	public void delete(String[] selectedIdLists) {
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
			String sql = "DELETE FROM t_address.t_address WHERE id = ?";

			/* 取得したIDの数分削除処理を実行 */
			String selectedId = null;
			for (String id : selectedIdLists) {
				/* SQL文をDBへ送信 */
				PreparedStatement ps = con.prepareStatement(sql);
				selectedId = id;
				ps.setString(1, selectedId);
				ps.executeUpdate();
			}

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
