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
		ArrayList<SearchBean> searchlist01 = new ArrayList<SearchBean>();

		/* JDBC�h���C�o�̓ǂݍ��� */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			/* DB�ڑ� */
			con = DriverManager.getConnection(url, user, pass);

			/* SQL�� */
			String sql = "select * from t_address.t_address WHERE id = ?";

			// �t�H�[������擾������������*/
			String id = ib.getId();

			/* SQL����DB�֑��M */
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);

			/* �������ʂ��擾 */
			ResultSet rs = ps.executeQuery();

			/* �������ʂ����X�g�Ɋi�[ */
			while (rs.next()) {
				SearchBean sb = new SearchBean();
				sb.setId(rs.getInt("id"));
				sb.setName(rs.getString("name"));
				sb.setAge(rs.getInt("age"));
				if(rs.getString("sex").equals("�j")) {
					sb.setSex("male");
				}else if(rs.getString("sex").equals("��")){
					sb.setSex("female");
				}else {
					sb.setSex("");
				}
				if (rs.getString("job") == null) {
					sb.setJob("0");
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
				searchlist01.add(sb);
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
		return searchlist01;
	}
}
