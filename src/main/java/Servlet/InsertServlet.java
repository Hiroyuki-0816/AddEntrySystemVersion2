package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InsertSearchBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.JobDao;
import Dao.SearchDao01;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/AddEntrySystemVersion2/Insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String job = request.getParameter("job");
		String tell = request.getParameter("tell");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("addressdetail");

		// �G���[�`�F�b�N
		ArrayList<String> errorMessages = new ArrayList<String>();
		String msg = "�͕K�����͂��Ă��������B";
		String msg2 = "�Ɍ�肪����܂��B���͂������������������܂��B";

		if (id == "") {
			errorMessages.add("�o�^ID" + msg);
		} else if (id.length() > 8) {
			errorMessages.add("�o�^ID" + msg2);
		}

		if (name == "") {
			errorMessages.add("����" + msg);
		} else if (name.length() > 20) {
			errorMessages.add("����" + msg2);
		}

		if (age == "") {
			errorMessages.add("�N��" + msg);
		} else if (age.length() > 3) {
			errorMessages.add("�N��" + msg2);
		}

		if (tell == "") {
			errorMessages.add("�d�b�ԍ�" + msg);
		} else if (tell.length() > 13) {
			errorMessages.add("�d�b�ԍ�" + msg2);
		} else if (tell.startsWith("-") || tell.endsWith("-")) {
			errorMessages.add("�n�C�t��'-'�ŊJ�n�A�܂��͏I�����镶����̓o�^�͂ł��܂���B");
		}

		if (zip == "") {
			errorMessages.add("�X�֔ԍ�" + msg);
		} else if (zip.length() > 8) {
			errorMessages.add("�X�֔ԍ�" + msg2);
		} else if (!zip.matches("[0-9]{3}-[0-9]{4}")) {
			errorMessages.add("�X�֔ԍ��͏����F�u999-9999�v�łȂ���Γo�^�ł��܂���B");
		}

		if (address == "") {
			errorMessages.add("�Z��" + msg);
		} else if (address.length() > 20) {
			errorMessages.add("�Z��" + msg2);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("�Ԓn" + msg2);
		}

		if (errorMessages.size() != 0) {
			/* �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�Ɋi�[ */
			request.setAttribute("errorMessages", errorMessages);
			/* ���͍��ڂ�ێ� */
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("age", age);
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
			request.getRequestDispatcher("./EntryError.jsp").forward(request, response);
		}

		if (errorMessages.size() == 0) {
			// �o�^ID���i�[����C���X�^���X
			InsertSearchBean isb = new InsertSearchBean();

			// �t�H�[�����œ��͂��ꂽ�o�^ID�������l�Ƃ��ăZ�b�g����
			isb.setId(id);

			/* �o�^ID���d�����Ă���f�[�^���Ȃ������� */
			SearchDao01 sdao01 = new SearchDao01();

			/* �������ʂ��擾 */
			ArrayList<SearchBean> searchlist = sdao01.insertSearch(isb);

			/* �o�^���������{���邩�X�V���������{���邩���f���� */
			if (searchlist.size() == 0) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

}