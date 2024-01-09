package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ArgumentBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.JobDao;
import Dao.SearchDao;

/**
 * Servlet implementation class test03
 */
@WebServlet("/AddEntrySystemVersion2/Search01")
public class SearchServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String idfrom = request.getParameter("idfrom");
		String idto = request.getParameter("idto");
		String name = request.getParameter("name");
		String agefrom = request.getParameter("agefrom");
		String ageto = request.getParameter("ageto");
		String sex = request.getParameter("sex");
		String job = request.getParameter("job");
		String tell = request.getParameter("tell");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("addressdetail");

		// �G���[�`�F�b�N
		ArrayList<String> errorMessages = new ArrayList<String>();
		String msg = "�Ɍ�肪����܂��B���͂������������������܂��B";
		String msg2 = "�Ɍ�肪����܂��B���͂��������̌^���Ⴂ�܂��B";
		String msg3 = "�͈͎̔w��Ɍ�肪����܂�";

		if (idfrom.length() > 8) {
			errorMessages.add("�o�^IDFROM" + msg);
		} else if (idfrom != "" && !idfrom.matches("^[0-9]+$")) {
			errorMessages.add("�o�^IDFROM" + msg2);
		}

		if (idto.length() > 8) {
			errorMessages.add("�o�^IDTO" + msg);
		} else if (idto != "" && !idto.matches("^[0-9]+$")) {
			errorMessages.add("�o�^IDTO" + msg2);
		}

		if ((idfrom != "" && idto != "") && (idfrom.matches("^[0-9]+$") && idto.matches("^[0-9]+$"))
				&& Integer.parseInt(idfrom) > Integer.parseInt(idto)) {
			errorMessages.add("�o�^ID" + msg3);
		}

		if (name.length() > 20) {
			errorMessages.add("����" + msg);
		}

		if (agefrom.length() > 3) {
			errorMessages.add("�N��FROM" + msg);
		} else if (agefrom != "" && !agefrom.matches("^[0-9]+$")) {
			errorMessages.add("�N��FROM" + msg2);
		}

		if (ageto.length() > 3) {
			errorMessages.add("�N��TO" + msg);
		} else if (ageto != "" && !ageto.matches("^[0-9]+$")) {
			errorMessages.add("�N��TO" + msg2);
		}

		if ((agefrom != "" && ageto != "") && (agefrom.matches("^[0-9]+$") && ageto.matches("^[0-9]+$"))
				&& Integer.parseInt(agefrom) > Integer.parseInt(ageto)) {
			errorMessages.add("�N��" + msg3);
		}

		if (tell.length() > 13) {
			errorMessages.add("�d�b�ԍ�" + msg);
		} else if (tell != "" && !tell.matches("^[-0-9]+$")) {
			errorMessages.add("�d�b�ԍ�" + msg2);
		}

		if (zip.length() > 8) {
			errorMessages.add("�X�֔ԍ�" + msg);
		} else if (zip != "" && !zip.matches("^[-0-9]+$")) {
			errorMessages.add("�X�֔ԍ�" + msg2);
		}

		if (address.length() > 20) {
			errorMessages.add("�Z��" + msg);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("�Ԓn" + msg);
		}

			// �����l���i�[����C���X�^���X
			ArgumentBean ab = new ArgumentBean();

			// �t�H�[�����œ��͂��ꂽ�l�������l�Ƃ��ăZ�b�g����
			ab.setIdfrom(idfrom);
			ab.setIdto(idto);
			ab.setName(name);
			ab.setAgefrom(agefrom);
			ab.setAgeto(ageto);
			ab.setSex(sex);
			ab.setJob(job);
			ab.setTell(tell);
			ab.setZip(zip);
			ab.setAddress(address);
			ab.setAddressdetail(addressdetail);

			/* �f�[�^�x�[�X�ɑ΂��Č������������{ */
			SearchDao sdao = new SearchDao();

			/* �������ʂ��擾 */
			ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

			/*�������ʂ�0���̏ꍇ�ɕ\��*/
			if (errorMessages.size() == 0 && searchlist.size() == 0) {
				errorMessages.add("�Y������f�[�^�����݂��܂���B");
			}

			/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
			request.setAttribute("searchlist", searchlist);

		/* �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�Ɋi�[ */
		request.setAttribute("errorMessages", errorMessages);

		/* ����������ێ� */
		request.setAttribute("idfrom", idfrom);
		request.setAttribute("idto", idto);
		request.setAttribute("name", name);
		request.setAttribute("agefrom", agefrom);
		request.setAttribute("ageto", ageto);
		request.setAttribute("sex", sex);
		request.setAttribute("job", job);
		request.setAttribute("tell", tell);
		request.setAttribute("zip", zip);
		request.setAttribute("address", address);
		request.setAttribute("addressdetail", addressdetail);

		/* �E�ƃ��X�g���ĕ\�� */
		JobDao jdao = new JobDao();
		ArrayList<JobBean> joblist = jdao.selectJob();
		request.setAttribute("joblist", joblist);

		// �t�H���[�h�̎��s
		request.getRequestDispatcher("./Search01.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
