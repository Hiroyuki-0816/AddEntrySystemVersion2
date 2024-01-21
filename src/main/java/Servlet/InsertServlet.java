package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.InsertBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.InsertDao;
import Dao.JobDao;
import Dao.SearchDao01;
import Dao.UpdateDao;
import Validation.InsertValidation;

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

		/* �t�H�[��������͒l���擾 */
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String job = request.getParameter("job");
		String tell = request.getParameter("tell");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("addressdetail");

		/* ���͒l���i�[����C���X�^���X */
		InsertBean ib = new InsertBean();

		/* �t�H�[�����œ��͂��ꂽ�l��o�^�l�Ƃ��ăZ�b�g���� */
		ib.setId(id);
		ib.setName(name);
		ib.setAge(age);
		ib.setSex(sex);
		ib.setJob(job);
		ib.setTell(tell);
		ib.setZip(zip);
		ib.setAddress(address);
		ib.setAddressDetail(addressdetail);

		/* �G���[�`�F�b�N�����{ */
		InsertValidation ivalidate = new InsertValidation();

		/* �G���[�`�F�b�N�̌��ʂ��擾 */
		ArrayList<String> errorMessages = ivalidate.errorCheckI(ib);

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

			/* �t�H���[�h�̎��s */
			request.getRequestDispatcher("./EntryError.jsp").forward(request, response);
		}

		if (errorMessages.size() == 0) {

			/* �o�^ID���d�����Ă���f�[�^���Ȃ������� */
			SearchDao01 sdao01 = new SearchDao01();

			/* �������ʂ��擾 */
			ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);

			/* �o�^���������{���邩�X�V���������{���邩���f���� */
			if (searchlist01.size() == 0) {

				/* �f�[�^�x�[�X�ɑ΂��ēo�^���������{ */
				InsertDao idao = new InsertDao();
				idao.insert(ib);

			} else {

				/* �f�[�^�x�[�X�ɑ΂��čX�V���������{ */
				UpdateDao udao = new UpdateDao();
				udao.update(ib);
			}

			/* �E�ƃ��X�g���ĕ\�� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);
			
			/* ������ʂ֑J��*/
			request.getRequestDispatcher("./Search.jsp").forward(request, response);

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
