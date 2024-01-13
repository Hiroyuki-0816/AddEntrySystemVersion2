package Dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.InsertSearchBean;
import Bean.SearchBean;

public class SearchDao01 {
	/* データベースのURL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DBにアクセスするユーザ */
	private final String user = "root";
	/* パスワード */
	private final String pass = "password";
	/* 初期のDB接続状態 */
	Connection con = null;

	public ArrayList<SearchBean> insertSearch(InsertSearchBean isb) {

		/* 検索結果を格納するリスト */
		ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();

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
			String sql = "select * from t_address.t_address WHERE id = ?";

			// フォームから取得した検索条件*/
			String id = isb.getId();

			/* SQL文をDBへ送信 */
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);

			/* 検索結果を取得 */
			ResultSet rs = ps.executeQuery();

			/* 検索結果をリストに格納 */
			while (rs.next()) {
				SearchBean sb = new SearchBean();
				sb.setId(rs.getInt("id"));
				searchlist.add(sb);
			}

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
		return searchlist;
	}
}
