package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDao {
	/* �f�[�^�x�[�X��URL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DB�ɃA�N�Z�X���郆�[�U */
	private final String user = "root";
	/* �p�X���[�h */
	private final String pass = "password";
	/* ������DB�ڑ���� */
	Connection con = null;
	/* ������A�����邩�𔻒� */
	boolean conbine = false;
	
	public void delete(String[] selectedIdLists) {
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
			String sql = "DELETE FROM t_address.t_address";
			
			/* SQL����DB�֑��M */
			PreparedStatement ps = con.prepareStatement(sql);
			
			/* �����̏��� */
			int seq = 0;
			
			/* �擾����ID�̐����폜���������s */
			String selectedId = null;
			for (String id : selectedIdLists) {
				++seq;
				selectedId = id;
				sql += Join(conbine) + " id = ? ";
				conbine = true;
				ps.setString(seq, selectedId);
			}

			// �폜�������s
			ps.executeUpdate();
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
	}
	/* �������̘A���𔻒� */
	private String Join(boolean conbine) {
		if (conbine) {
			return " AND ";
		} else {
			return " WHERE ";
		}
	}
}
