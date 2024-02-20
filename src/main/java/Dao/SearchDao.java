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
	/* 条件を連結するかを判定 */
	boolean conbine = false;

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
			String sql = "SELECT t_address.id,name,age,sex,m_job.job,tell,zip,address,addressdetail FROM t_address.t_address left join t_address.m_job on t_address.job = m_job.id";

			/* フォームから取得した検索条件 */
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

			if (idfrom != "" && idto != "") {
				sql += Join(conbine) + "t_address.id between ? and ?";
				conbine = true;
			} else if ((idfrom != "" && idto == "") || (idfrom == "" && idto != "")) {
				sql += Join(conbine) + "t_address.id = ?";
				conbine = true;
			}

			if (name != "") {
				sql += Join(conbine) + "name LIKE ?";
				conbine = true;
			}

			if (agefrom != "" && ageto != "") {
				sql += Join(conbine) + "age between ? and ?";
				conbine = true;
			} else if ((agefrom != "" && ageto == "") || (agefrom == "" && ageto != "")) {
				sql += Join(conbine) + "age = ?";
				conbine = true;
			}

			if (!sex.equals("both")) {
				sql += Join(conbine) + "sex = ?";
				conbine = true;
			}

			if (!job.equals("0")) {
				sql += Join(conbine) + "m_job.job = ?";
				conbine = true;
			}

			if (tell != "") {
				if (!tell.contains("-")) {
					sql += Join(conbine) + "replace (tell,'-','') LIKE ?";
					conbine = true;
				} else {
					sql += Join(conbine) + "tell LIKE ?";
					conbine = true;
				}
			}

			if (zip != "") {
				if (!zip.contains("-")) {
					sql += Join(conbine) + "replace (zip,'-','') LIKE ?";
					conbine = true;
				} else {
					sql += Join(conbine) + "zip LIKE ?";
					conbine = true;
				}
			}

			if (address != "") {
				sql += Join(conbine) + "address LIKE ?";
				conbine = true;
			}

			if (addressdetail != "") {
				sql += Join(conbine) + "addressdetail LIKE ?";
				conbine = true;
			}

			/* SQL文をDBへ送信 */
			PreparedStatement ps = con.prepareStatement(sql);

			/* 引数の順番 */
			int seq = 0;

			/* パラメータを追加 */
			if (idfrom != "" && idto != "") {
				ps.setString(++seq, idfrom);
				ps.setString(++seq, idto);
			} else if (idfrom != "" && idto == "") {
				ps.setString(++seq, idfrom);
			} else if (idfrom == "" && idto != "") {
				ps.setString(++seq, idto);
			}

			if (name != "") {
				ps.setString(++seq, "%" + name + "%");
			}

			if (agefrom != "" && ageto != "") {
				ps.setString(++seq, agefrom);
				ps.setString(++seq, ageto);
			} else if (agefrom != "" && ageto == "") {
				ps.setString(++seq, agefrom);
			} else if (agefrom == "" && ageto != "") {
				ps.setString(++seq, ageto);
			}

			if (sex.equals("male")) {
				ps.setString(++seq, "男");
			} else if (sex.equals("female")) {
				ps.setString(++seq, "女");
			}

			switch (job) {
			case "1":
				ps.setString(++seq, "01:会社員");
				break;
			case "2":
				ps.setString(++seq, "02:公務員");
				break;
			case "3":
				ps.setString(++seq, "03:自営業");
				break;
			case "4":
				ps.setString(++seq, "04:個人事業主");
				break;
			case "5":
				ps.setString(++seq, "05:経営者・会社役員");
				break;
			case "6":
				ps.setString(++seq, "06:パート・アルバイト");
				break;
			case "7":
				ps.setString(++seq, "07:専業主婦・主夫");
				break;
			case "8":
				ps.setString(++seq, "08:学生");
				break;
			case "9":
				ps.setString(++seq, "09:その他");
				break;
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
				if (rs.getString("job") == null) {
					sb.setJob("");
				} else {
					sb.setJob(rs.getString("job"));
				}
				sb.setTell(rs.getString("tell"));
				sb.setZip(rs.getString("zip"));
				sb.setAddress(rs.getString("address"));
				if (rs.getString("addressdetail") == null) {
					sb.setAddressDetail("");
				} else {
					sb.setAddressDetail(rs.getString("addressdetail"));
				}
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

	/* 条件文の連結を判定 */
	private String Join(boolean conbine) {
		if (conbine) {
			return " AND ";
		} else {
			return " WHERE ";
		}
	}
}
