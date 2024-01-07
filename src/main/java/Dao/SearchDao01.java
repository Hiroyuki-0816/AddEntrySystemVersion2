package Dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.InsertBean;
import Bean.SearchBean;

public class SearchDao01 {
	/* �f�[�^�x�[�X��URL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DB�ɃA�N�Z�X���郆�[�U */
	private final String user = "root";
	/* �p�X���[�h */
	private final String pass = "password";
	/* ������DB�ڑ���� */
	Connection con = null;
	
	public ArrayList<SearchBean> insertSearch(InsertBean ib) {
		
		/* �������ʂ��i�[���郊�X�g */
		ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
		
		/* JDBC�h���C�o�̓ǂݍ��� */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			/* DB�ڑ� */
			con = DriverManager.getConnection(url, user, pass);

			/*SQL��*/
			String sql = "select t_address.id,name,age,sex,m_job.job,tell,zip,address,addressdetail from t_address.t_address left join t_address.m_job on t_address.job = m_job.id WHERE t_address.id = ?";

			// �t�H�[������擾������������*/
			int id = ib.getId();

			/* SQL����DB�֑��M */
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			/* �������ʂ��擾 */
			ResultSet rs = ps.executeQuery();

			/* �������ʂ����X�g�Ɋi�[ */
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
			// DB�ڑ�������
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
