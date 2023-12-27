package Dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.ArgumentBean;
import Bean.SearchBean;

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
	public ArrayList<SearchBean> selectSearch(ArgumentBean ab){
		
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
			Integer idfrom = ab.getIdfrom();
			Integer idto = ab.getIdto();
			String name = ab.getName();
			Integer agefrom = ab.getAgefrom();
			Integer ageto = ab.getAgeto();
			String tell = ab.getTell();
			String zip = ab.getZip();
			String address = ab.getAddress();
			String addressdetail = ab.getAddressdetail();
			
			/*条件を連結するかを判定*/
			boolean conbine = false;
			
			
			if(idfrom != null && idto != null) {
				sql += " WHERE t_address.id between ? and ?";
                conbine = true;
			}else if((idfrom != null && idto == null) || (idfrom == null && idto != null)) {
				sql += " WHERE t_address.id = ?";
                conbine = true;
			}
			
			if(name != "") {
				if(conbine) {
					sql += " AND name LIKE ?";
	            }else {
	            	sql += " WHERE name LIKE ?";
	                conbine = true;
	            }
			}
			
			if(agefrom != null && ageto != null) {
				if(conbine) {
					sql += " AND age between ? and ?";
				}else {
					sql += " WHERE age between ? and ?";
					conbine = true;
				}
			}else if((agefrom != null && ageto == null) || (agefrom == null && ageto != null)) {
				if(conbine) {
					sql += " AND age = ?";
				}else {
					sql += " WHERE age = ?";
					conbine = true;
				}
			}
			
			if(tell != "") {
				if(conbine) {
	                sql += " AND tell LIKE ?";
				}else{
					sql += " WHERE tell LIKE ?"; 
					conbine = true;
				}
			}
			
			if(zip != "") {
				if(conbine) {
	                sql += " AND zip LIKE ?";
				}else{
					sql += " WHERE zip LIKE ?"; 
					conbine = true;
				}
			}
			
			if(address != "") {
				if(conbine) {
	                sql += " AND address LIKE ?";
				}else{
					sql += " WHERE address LIKE ?"; 
					conbine = true;
				}
			}
			
			if(addressdetail != "") {
				if(conbine) {
	                sql += " AND addressdetail LIKE ?";
				}else{
					sql += " WHERE addressdetail LIKE ?"; 
					conbine = true;
				}
			}
			
			/*SQL文をDBへ送信*/
			PreparedStatement ps = con.prepareStatement(sql);
			
			/*引数の順番*/
			int seq = 0;
			
			/*パラメータを追加*/
			if(idfrom != null && idto != null) {
				ps.setInt(++seq, idfrom);
				ps.setInt(++seq, idto);
			}
			if(idfrom != null && idto == null) {
				ps.setInt(++seq, idfrom);
			}
			if(idfrom == null && idto != null) {
				ps.setInt(++seq, idto);
			}
			
			if(name != "") {
				ps.setString(++seq, "%" + name + "%");
			}
			
			if(agefrom != null && ageto != null) {
				ps.setInt(++seq, agefrom);
				ps.setInt(++seq, ageto);
			}
			if(agefrom != null && ageto == null) {
				ps.setInt(++seq, agefrom);
			}
			if(agefrom == null && ageto != null) {
				ps.setInt(++seq, ageto);
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
