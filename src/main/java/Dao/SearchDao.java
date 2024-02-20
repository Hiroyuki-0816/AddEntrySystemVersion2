package Dao;

import java.sql.*;
import java.util.ArrayList;

import Bean.ArgumentBean;
import Bean.SearchBean;

public class SearchDao {
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

	/* �������ʂ��擾���郁�\�b�h */
	public ArrayList<SearchBean> selectSearch(ArgumentBean ab) {

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

			/* �f�t�H���g��SQL��(�S�����������l) */
			String sql = "SELECT t_address.id,name,age,sex,m_job.job,tell,zip,address,addressdetail FROM t_address.t_address left join t_address.m_job on t_address.job = m_job.id";

			/* �t�H�[������擾������������ */
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

			if (idfrom != "" && idto != "") {
				sql += Join(conbine) + "t_address.id between ? and ?";
				conbine = true;
			} else if ((idfrom != "" && idto == "") || (idfrom == "" && idto != "")) {
				sql += Join(conbine) + "t_address.id = ?";
				conbine = true;
			}

			if (name != "") {
				sql += Join(conbine) + "name LIKE ?";
				conbine = true;
			}

			if (agefrom != "" && ageto != "") {
				sql += Join(conbine) + "age between ? and ?";
				conbine = true;
			} else if ((agefrom != "" && ageto == "") || (agefrom == "" && ageto != "")) {
				sql += Join(conbine) + "age = ?";
				conbine = true;
			}

			if (!sex.equals("both")) {
				sql += Join(conbine) + "sex = ?";
				conbine = true;
			}

			if (!job.equals("0")) {
				sql += Join(conbine) + "m_job.job = ?";
				conbine = true;
			}

			if (tell != "") {
				if (!tell.contains("-")) {
					sql += Join(conbine) + "replace (tell,'-','') LIKE ?";
					conbine = true;
				} else {
					sql += Join(conbine) + "tell LIKE ?";
					conbine = true;
				}
			}

			if (zip != "") {
				if (!zip.contains("-")) {
					sql += Join(conbine) + "replace (zip,'-','') LIKE ?";
					conbine = true;
				} else {
					sql += Join(conbine) + "zip LIKE ?";
					conbine = true;
				}
			}

			if (address != "") {
				sql += Join(conbine) + "address LIKE ?";
				conbine = true;
			}

			if (addressdetail != "") {
				sql += Join(conbine) + "addressdetail LIKE ?";
				conbine = true;
			}

			/* SQL����DB�֑��M */
			PreparedStatement ps = con.prepareStatement(sql);

			/* �����̏��� */
			int seq = 0;

			/* �p�����[�^��ǉ� */
			if (idfrom != "" && idto != "") {
				ps.setString(++seq, idfrom);
				ps.setString(++seq, idto);
			} else if (idfrom != "" && idto == "") {
				ps.setString(++seq, idfrom);
			} else if (idfrom == "" && idto != "") {
				ps.setString(++seq, idto);
			}

			if (name != "") {
				ps.setString(++seq, "%" + name + "%");
			}

			if (agefrom != "" && ageto != "") {
				ps.setString(++seq, agefrom);
				ps.setString(++seq, ageto);
			} else if (agefrom != "" && ageto == "") {
				ps.setString(++seq, agefrom);
			} else if (agefrom == "" && ageto != "") {
				ps.setString(++seq, ageto);
			}

			if (sex.equals("male")) {
				ps.setString(++seq, "�j");
			} else if (sex.equals("female")) {
				ps.setString(++seq, "��");
			}

			switch (job) {
			case "1":
				ps.setString(++seq, "01:��Ј�");
				break;
			case "2":
				ps.setString(++seq, "02:������");
				break;
			case "3":
				ps.setString(++seq, "03:���c��");
				break;
			case "4":
				ps.setString(++seq, "04:�l���Ǝ�");
				break;
			case "5":
				ps.setString(++seq, "05:�o�c�ҁE��Ж���");
				break;
			case "6":
				ps.setString(++seq, "06:�p�[�g�E�A���o�C�g");
				break;
			case "7":
				ps.setString(++seq, "07:��Ǝ�w�E��v");
				break;
			case "8":
				ps.setString(++seq, "08:�w��");
				break;
			case "9":
				ps.setString(++seq, "09:���̑�");
				break;
			}

			if (tell != "") {
				ps.setString(++seq, tell + "%");
			}
			if (zip != "") {
				ps.setString(++seq, zip + "%");
			}
			if (address != "") {
				ps.setString(++seq, "%" + address + "%");
			}
			if (addressdetail != "") {
				ps.setString(++seq, "%" + addressdetail + "%");
			}

			/* �������ʂ��擾 */
			ResultSet rs = ps.executeQuery();

			/* �������ʂ����X�g�Ɋi�[ */
			while (rs.next()) {
				SearchBean sb = new SearchBean();
				sb.setId(rs.getInt("id"));
				sb.setName(rs.getString("name"));
				sb.setAge(rs.getInt("age"));
				sb.setSex(rs.getString("sex"));
				if (rs.getString("job") == null) {
					sb.setJob("");
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

	/* �������̘A���𔻒� */
	private String Join(boolean conbine) {
		if (conbine) {
			return " AND ";
		} else {
			return " WHERE ";
		}
	}
}
