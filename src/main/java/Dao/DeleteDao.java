package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DeleteDao {
	/* �f�[�^�x�[�X��URL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DB�ɃA�N�Z�X���郆�[�U */
	private final String user = "root";
	/* �p�X���[�h */
	private final String pass = "password";
	/* ������DB�ڑ���� */
	Connection con = null;

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
			String sql = "DELETE FROM t_address.t_address WHERE id = ?";

			/* �擾����ID�̐����폜���������s */
			String selectedId = null;
			for (String id : selectedIdLists) {
				/* SQL����DB�֑��M */
				PreparedStatement ps = con.prepareStatement(sql);
				selectedId = id;
				ps.setString(1, selectedId);
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB�ڑ�������
			if (!Objects.isNull(con)) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
