package Dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.ArgumentBean;
import Bean.SearchBean;

public class SearchDao {
	/* データベースのURL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DBにアクセスするユーザ */
	private final String user = "root";
	/* パスワード */
	private final String pass = "password";
	/* 初期のDB接続状態 */
	Connection con = null;

	/* 検索結果を取得するメソッド */
	public ArrayList<SearchBean> selectSearch(ArgumentBean ab) {

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

			/* デフォルトのSQL文(全件検索も同様) */
			String sql = "select t_address.id,name,age,sex,m_job.job,tell,zip,address,addressdetail from t_address.t_address left join t_address.m_job on t_address.job = m_job.id";

			// フォームから取得した検索条件*/
			String idfrom = ab.getIdfrom();
			String idto = ab.getIdto();
			String name = ab.getName();
			String agefrom = ab.getAgefrom();
			String ageto = ab.getAgeto();
			String sex = ab.getSex();
			String job = ab.getJob();
			String tell = ab.getTell();
			String zip = ab.getZip();
			String address = ab.getAddress();
			String addressdetail = ab.getAddressdetail();

			/* 条件を連結するかを判定 */
			boolean conbine = false;

			if (idfrom != "" && idto != "") {
				sql += " WHERE t_address.id between ? and ?";
				conbine = true;
			} else if ((idfrom != "" && idto == "") || (idfrom == "" && idto != "")) {
				sql += " WHERE t_address.id = ?";
				conbine = true;
			}

			if (name != "") {
				if (conbine) {
					sql += " AND name LIKE ?";
				} else {
					sql += " WHERE name LIKE ?";
					conbine = true;
				}
			}

			if (agefrom != "" && ageto != "") {
				if (conbine) {
					sql += " AND age between ? and ?";
				} else {
					sql += " WHERE age between ? and ?";
					conbine = true;
				}
			} else if ((agefrom != "" && ageto == "") || (agefrom == "" && ageto != "")) {
				if (conbine) {
					sql += " AND age = ?";
				} else {
					sql += " WHERE age = ?";
					conbine = true;
				}
			}

			if (sex.equals("male")) {
				if (conbine) {
					sql += " AND sex = ? ";
				} else {
					sql += " WHERE sex = ? ";
					conbine = true;
				}
			} else if (sex.equals("female")) {
				if (conbine) {
					sql += " AND sex =　? ";
				} else {
					sql += " WHERE sex = ? ";
					conbine = true;
				}
			}

			if (!job.equals("0")) {
				if (conbine) {
					sql += " AND m_job.job =　? ";
				} else {
					sql += " WHERE m_job.job = ? ";
					conbine = true;
				}
			}

			if (tell != "") {
				if (!tell.contains("-")) {
					if (conbine) {
						sql += " AND replace (tell,'-','') LIKE ?";
					} else {
						sql += " WHERE replace (tell,'-','') LIKE ?";
						conbine = true;
					}
				} else {
					if (conbine) {
						sql += " AND tell LIKE ?";
					} else {
						sql += " WHERE tell LIKE ?";
						conbine = true;
					}
				}
			}

			if (zip != "") {
				if (!zip.contains("-")) {
					if (conbine) {
						sql += " AND replace (zip,'-','') LIKE ?";
					} else {
						sql += " WHERE replace (zip,'-','') LIKE ?";
						conbine = true;
					}
				} else {
					if (conbine) {
						sql += " AND zip LIKE ?";
					} else {
						sql += " WHERE zip LIKE ?";
						conbine = true;
					}
				}
			}

			if (address != "") {
				if (conbine) {
					sql += " AND address LIKE ?";
				} else {
					sql += " WHERE address LIKE ?";
					conbine = true;
				}
			}

			if (addressdetail != "") {
				if (conbine) {
					sql += " AND addressdetail LIKE ?";
				} else {
					sql += " WHERE addressdetail LIKE ?";
					conbine = true;
				}
			}

			/* SQL文をDBへ送信 */
			PreparedStatement ps = con.prepareStatement(sql);

			/* 引数の順番 */
			int seq = 0;

			/* パラメータを追加 */
			if (idfrom != "" && idto != "") {
				ps.setString(++seq, idfrom);
				ps.setString(++seq, idto);
			}
			if (idfrom != "" && idto == "") {
				ps.setString(++seq, idfrom);
			}
			if (idfrom == "" && idto != "") {
				ps.setString(++seq, idto);
			}

			if (name != "") {
				ps.setString(++seq, "%" + name + "%");
			}

			if (agefrom != "" && ageto != "") {
				ps.setString(++seq, agefrom);
				ps.setString(++seq, ageto);
			}
			if (agefrom != "" && ageto == "") {
				ps.setString(++seq, agefrom);
			}
			if (agefrom == "" && ageto != "") {
				ps.setString(++seq, ageto);
			}

			if (sex.equals("male")) {
				ps.setString(++seq, "男");
			}
			if (sex.equals("female")) {
				ps.setString(++seq, "女");
			}

			if (job.equals("1")) {
				ps.setString(++seq, "01:会社員");
			}
			if (job.equals("2")) {
				ps.setString(++seq, "02:公務員");
			}
			if (job.equals("3")) {
				ps.setString(++seq, "03:自営業");
			}
			if (job.equals("4")) {
				ps.setString(++seq, "04:個人事業主");
			}
			if (job.equals("5")) {
				ps.setString(++seq, "05:経営者・会社役員");
			}
			if (job.equals("6")) {
				ps.setString(++seq, "06:パート・アルバイト");
			}
			if (job.equals("7")) {
				ps.setString(++seq, "07:専業主婦・主夫");
			}
			if (job.equals("8")) {
				ps.setString(++seq, "08:学生");
			}
			if (job.equals("9")) {
				ps.setString(++seq, "09:その他");
			}

			if (tell != "") {
				ps.setString(++seq, tell + "%");
			}
			if (zip != "") {
				ps.setString(++seq, zip + "%");
			}
			if (address != "") {
				ps.setString(++seq, "%" + address + "%");
			}
			if (addressdetail != "") {
				ps.setString(++seq, "%" + addressdetail + "%");
			}

			/* 検索結果を取得 */
			ResultSet rs = ps.executeQuery();

			/* 検索結果をリストに格納 */
			while (rs.next()) {
				SearchBean sb = new SearchBean();
				sb.setId(rs.getInt("id"));
				sb.setName(rs.getString("name"));
				sb.setAge(rs.getInt("age"));
				sb.setSex(rs.getString("sex"));
				sb.setJob(rs.getString("job"));
				sb.setTell(rs.getString("tell"));
				sb.setZip(rs.getString("zip"));
				sb.setAddress(rs.getString("address"));
				sb.setAddressDetail(rs.getString("addressdetail"));
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
