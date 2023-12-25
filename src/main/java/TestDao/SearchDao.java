package TestDao;

import java.sql.*;
import java.util.ArrayList;

import TestBean.SearchBean;

public class SearchDao {
	/*�f�[�^�x�[�X��URL*/
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/*DB�ɃA�N�Z�X���郆�[�U*/
	private final String user = "root";
	/*�p�X���[�h*/
	private final String pass = "password";
	/*�������ʂ��i�[���郊�X�g*/
	ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
	/*������DB�ڑ����*/
	Connection con = null;
	
	
	/*�������ʂ��擾���郁�\�b�h*/
	public ArrayList<SearchBean> selectSearch(SearchBean sb){
		
		/*JDBC�h���C�o�̓ǂݍ���*/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			/*DB�ڑ�*/
			con = DriverManager.getConnection(url,user,pass);
			
			/*�f�t�H���g��SQL��(�S�����������l)*/
			String sql = "select t_address.id,name,age,sex,m_job.job,tell,zip,address,addressdetail from t_address.t_address left join t_address.m_job on t_address.job = m_job.id";
			
			//�t�H�[������擾������������*/
			Integer agefrom = sb.getAge();
			Integer ageto = sb.getAge();
			String name = sb.getName();
			String tell = sb.getTell();
			String zip = sb.getZip();
			String address = sb.getAddress();
			String addressdetail = sb.getAddressDetail();
			
			/*������A�����邩�𔻒�*/
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
			
			/*SQL����DB�֑��M*/
			PreparedStatement ps = con.prepareStatement(sql);
			
			/*�����̏���*/
			int seq = 0;
			
			/*�p�����[�^��ǉ�*/
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
			
			/*�������ʂ��擾*/
			ResultSet rs = ps.executeQuery();
			
			/*�������ʂ����X�g�Ɋi�[*/
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
			//DB�ڑ�������
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
