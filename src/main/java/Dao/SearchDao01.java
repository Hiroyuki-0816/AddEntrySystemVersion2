package Dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.InsertSearchBean;
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

	public ArrayList<SearchBean> insertSearch(InsertSearchBean isb) {

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

			/* SQL�� */
			String sql = "select * from t_address.t_address WHERE id = ?";

			// �t�H�[������擾������������*/
			String id = isb.getId();

			/* SQL����DB�֑��M */
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);

			/* �������ʂ��擾 */
			ResultSet rs = ps.executeQuery();

			/* �������ʂ����X�g�Ɋi�[ */
			while (rs.next()) {
				SearchBean sb = new SearchBean();
				sb.setId(rs.getInt("id"));
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
