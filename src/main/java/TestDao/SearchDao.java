package TestDao;

import java.sql.*;
import java.util.ArrayList;

import TestBean.SearchBean;

public class SearchDao {
	/*データベースのURL*/
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/*DBにアクセスするユーザ*/
	private final String user = "root";
	/*パスワード*/
	private final String pass = "password";
	/*検索結果を格納するリスト*/
	ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
	/*初期のDB接続状態*/
	Connection con = null;
	
	
	/*検索結果を取得するメソッド*/
	public ArrayList<SearchBean> selectSearch(SearchBean sb){
		
		/*JDBCドライバの読み込み*/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			/*DB接続*/
			con = DriverManager.getConnection(url,user,pass);
			
			/*デフォルトのSQL文(全件検索も同様)*/
			String sql = "select t_address.id,name,age,sex,m_job.job,tell,zip,address,addressdetail from t_address.t_address left join t_address.m_job on t_address.job = m_job.id";
			
			//フォームから取得した検索条件*/
			Integer agefrom = sb.getAge();
			Integer ageto = sb.getAge();
			String name = sb.getName();
			String tell = sb.getTell();
			String zip = sb.getZip();
			String address = sb.getAddress();
			String addressdetail = sb.getAddressDetail();
			
			/*条件を連結するかを判定*/
			boolean conbine = false;
			
			if(agefrom != null && ageto != null) {
				sql += " WHERE age between ? and ?";
                conbine = true;
			}else if((agefrom != null && ageto == null) || (agefrom == null && ageto != null)) {
				sql += " WHERE age == ?";
                conbine = true;
			}
			
			if(name != "") {
				if(!conbine) {
	                sql += " WHERE name LIKE ?";
	                conbine = true;
	            }else {
	            	sql += " AND name LIKE ?";
	            }
			}
			
			if(tell != "") {
				if(!conbine) {
					sql += " WHERE tell LIKE ?"; 
					conbine = true;
				}else{
	                sql += " AND tell LIKE ?";
				}
			}
			
			if(zip != "") {
				if(!conbine) {
					sql += " WHERE zip LIKE ?"; 
					conbine = true;
				}else{
	                sql += " AND zip LIKE ?";
				}
			}
			
			if(address != "") {
				if(!conbine) {
					sql += " WHERE address LIKE ?"; 
					conbine = true;
				}else{
	                sql += " AND address LIKE ?";
				}
			}
			
			if(addressdetail != "") {
				if(!conbine) {
					sql += " WHERE addressdetail LIKE ?"; 
					conbine = true;
				}else{
	                sql += " AND addressdetail LIKE ?";
				}
			}
			
			/*SQL文をDBへ送信*/
			PreparedStatement ps = con.prepareStatement(sql);
			
			/*引数の順番*/
			int seq = 0;
			
			/*パラメータを追加*/
			if(name != "") {
				ps.setString(++seq, "%" + name + "%");
			}
			if(tell != "") {
				ps.setString(++seq, tell + "%");
			}
			if(zip != "") {
				ps.setString(++seq, zip + "%");
			}
			if(address != "") {
				ps.setString(++seq, "%" + address + "%");
			}
			if(addressdetail != "") {
				ps.setString(++seq, "%" + addressdetail + "%");
			}
			
			/*検索結果を取得*/
			ResultSet rs = ps.executeQuery();
			
			/*検索結果をリストに格納*/
			while(rs.next()) {
				SearchBean sl = new SearchBean();
				sl.setId(rs.getInt("id"));
				sl.setName(rs.getString("name"));
				sl.setAge(rs.getInt("age"));
				sl.setSex(rs.getString("sex"));
				sl.setJob(rs.getString("job"));
				sl.setTell(rs.getString("tell"));
				sl.setZip(rs.getString("zip"));
				sl.setAddress(rs.getString("address"));
				sl.setAddressDetail(rs.getString("addressdetail"));
				searchlist.add(sl); 		
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DB接続を解除
			if(con != null) {
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
