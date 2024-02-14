package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDao {
	/* データベースのURL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DBにアクセスするユーザ */
	private final String user = "root";
	/* パスワード */
	private final String pass = "password";
	/* 初期のDB接続状態 */
	Connection con = null;
	/* 条件を連結するかを判定 */
	boolean conbine = false;
	
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
			String sql = "DELETE FROM t_address.t_address";
			
			/* SQL文をDBへ送信 */
			PreparedStatement ps = con.prepareStatement(sql);
			
			/* 引数の順番 */
			int seq = 0;
			
			/* 取得したIDの数分削除処理を実行 */
			String selectedId = null;
			for (String id : selectedIdLists) {
				++seq;
				selectedId = id;
				sql += Join(conbine) + " id = ? ";
				conbine = true;
				ps.setString(seq, selectedId);
			}

			// 削除処理実行
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
	/* 条件文の連結を判定 */
	private String Join(boolean conbine) {
		if (conbine) {
			return " AND ";
		} else {
			return " WHERE ";
		}
	}
}
