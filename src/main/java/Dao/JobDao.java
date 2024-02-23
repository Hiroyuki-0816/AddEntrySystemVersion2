package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import Bean.JobBean;

//�E�ƃ}�X�^�̈ꗗ���擾���邽�߂̃N���X
public class JobDao {
	/* �f�[�^�x�[�X��URL */
	private final String url = "jdbc:mysql://localhost:3306/t_address";
	/* DB�ɃA�N�Z�X���郆�[�U */
	private final String user = "root";
	/* �p�X���[�h */
	private final String pass = "password";
	/* DB����E�Ƃ��擾����SQL�� */
	private final String sql = "select * from t_address.m_job";
	/* �������ʂ��i�[���郊�X�g */
	ArrayList<JobBean> joblist = new ArrayList<JobBean>();
	/* ������DB�ڑ���� */
	Connection con = null;

	/* �������ʂ��擾���郁�\�b�h */
	public ArrayList<JobBean> selectJob() {
		try {
			/* JDBC�h���C�o�̓ǂݍ��� */
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			/* DB�ڑ� */
			con = DriverManager.getConnection(url, user, pass);

			/* SQL����DB�֑��M */
			PreparedStatement ps = con.prepareStatement(sql);

			/* �������ʂ��擾 */
			ResultSet rs = ps.executeQuery();

			/* �������ʂ����X�g�Ɋi�[ */
			while (rs.next()) {
				JobBean jb = new JobBean();
				jb.setId(rs.getInt("id"));
				jb.setJob(rs.getString("job"));
				joblist.add(jb);
			}

		} catch (Exception e) {
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
		return joblist;
	}

}
