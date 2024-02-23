package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import Bean.JobBean;

//職業マスタの一覧を取得するためのクラス
public class JobDao {
	/* データベースのURL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DBにアクセスするユーザ */
	private final String user = "root";
	/* パスワード */
	private final String pass = "password";
	/* DBから職業を取得するSQL文 */
	private final String sql = "select * from t_address.m_job";
	/* 検索結果を格納するリスト */
	ArrayList<JobBean> joblist = new ArrayList<JobBean>();
	/* 初期のDB接続状態 */
	Connection con = null;

	/* 検索結果を取得するメソッド */
	public ArrayList<JobBean> selectJob() {
		try {
			/* JDBCドライバの読み込み */
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			/* DB接続 */
			con = DriverManager.getConnection(url, user, pass);

			/* SQL文をDBへ送信 */
			PreparedStatement ps = con.prepareStatement(sql);

			/* 検索結果を取得 */
			ResultSet rs = ps.executeQuery();

			/* 検索結果をリストに格納 */
			while (rs.next()) {
				JobBean jb = new JobBean();
				jb.setId(rs.getInt("id"));
				jb.setJob(rs.getString("job"));
				joblist.add(jb);
			}

		} catch (Exception e) {
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
		return joblist;
	}

}
