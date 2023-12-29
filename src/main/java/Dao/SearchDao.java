package Dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.ArgumentBean;
import Bean.SearchBean;

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
	public ArrayList<SearchBean> selectSearch(ArgumentBean ab){
		
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
			
			/*������A�����邩�𔻒�*/
			boolean conbine = false;
			
			
			if(idfrom != "" && idto != "") {
				sql += " WHERE t_address.id between ? and ?";
                conbine = true;
			}else if((idfrom != "" && idto == "") || (idfrom == "" && idto != "")) {
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
			
			if(agefrom != "" && ageto != "") {
				if(conbine) {
					sql += " AND age between ? and ?";
				}else {
					sql += " WHERE age between ? and ?";
					conbine = true;
				}
			}else if((agefrom != "" && ageto == "") || (agefrom == "" && ageto != "")) {
				if(conbine) {
					sql += " AND age = ?";
				}else {
					sql += " WHERE age = ?";
					conbine = true;
				}
			}
			
			if(sex.equals("male")) {
				if(conbine) {
					sql += " AND sex = ? ";
				}else {
					sql += " WHERE sex = ? ";
					conbine = true;
				}
			}else if(sex.equals("female")) {
				if(conbine) {
					sql += " AND sex =�@? ";
				}else {
					sql += " WHERE sex = ? ";
					conbine = true;
				}
			}
			
			if(!job.equals("00")) {
				if(conbine) {
					sql += " AND m_job.job =�@? ";
				}else {
					sql += " WHERE m_job.job = ? ";
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
			
			/*SQL����DB�֑��M*/
			PreparedStatement ps = con.prepareStatement(sql);
			
			/*�����̏���*/
			int seq = 0;
			
			/*�p�����[�^��ǉ�*/
			if(idfrom != "" && idto != "") {
				ps.setString(++seq, idfrom);
				ps.setString(++seq, idto);
			}
			if(idfrom != "" && idto == "") {
				ps.setString(++seq, idfrom);
			}
			if(idfrom == "" && idto != "") {
				ps.setString(++seq, idto);
			}
			
			if(name != "") {
				ps.setString(++seq, "%" + name + "%");
			}
			
			if(agefrom != "" && ageto != "") {
				ps.setString(++seq, agefrom);
				ps.setString(++seq, ageto);
			}
			if(agefrom != "" && ageto == "") {
				ps.setString(++seq, agefrom);
			}
			if(agefrom == "" && ageto != "") {
				ps.setString(++seq, ageto);
			}
			
			if(sex.equals("male")) {
				ps.setString(++seq, "�j");
			}
			if(sex.equals("female")) {
				ps.setString(++seq, "��");
			}
			
			if(job.equals("1")) {
				ps.setString(++seq, "01:��Ј�");
			}
			if(job.equals("2")) {
				ps.setString(++seq, "02:������");
			}
			if(job.equals("3")) {
				ps.setString(++seq, "03:���c��");
			}
			if(job.equals("4")) {
				ps.setString(++seq, "04:�l���Ǝ�");
			}
			if(job.equals("5")) {
				ps.setString(++seq, "05:�o�c�ҁE��Ж���");
			}
			if(job.equals("6")) {
				ps.setString(++seq, "06:�p�[�g�E�A���o�C�g");
			}
			if(job.equals("7")) {
				ps.setString(++seq, "07:��Ǝ�w�E��v");
			}
			if(job.equals("8")) {
				ps.setString(++seq, "08:�w��");
			}
			if(job.equals("9")) {
				ps.setString(++seq, "09:���̑�");
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