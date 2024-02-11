package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.ArgumentBean;
import Bean.InsertBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.InsertDao;
import Dao.JobDao;
import Dao.SearchDao;
import Dao.SearchDao01;
import Dao.UpdateDao;
import Validation.InsertValidation;
import Validation.SearchValidation;

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

		/* �{�^���̒l�ŏ����𕪊� */
		String submitType = request.getParameter("button");

		if (Objects.equals(submitType, "�o�^")) {
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

				/* �o�^ID�̓��͗��̕ҏW�̉ۂ𐧌� */
				HttpSession session = request.getSession();
				String submitTypeS = (String) session.getAttribute("submitTypeS");
				if (Objects.equals(submitTypeS, "�ύX")) {
					request.setAttribute("readonly", "update");
				} else {
					request.setAttribute("readonly", "insert");
				}

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
				request.getRequestDispatcher("./Entry.jsp").forward(request, response);
			} else if (errorMessages.size() == 0) {

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

				/* �Z�b�V���������擾 */
				HttpSession session = request.getSession();
				String idfromS = (String) session.getAttribute("idfromS");
				String idtoS = (String) session.getAttribute("idtoS");
				String nameS = (String) session.getAttribute("nameS");
				String agefromS = (String) session.getAttribute("agefromS");
				String agetoS = (String) session.getAttribute("agetoS");
				String sexS = (String) session.getAttribute("sexS");
				String jobS = (String) session.getAttribute("jobS");
				String tellS = (String) session.getAttribute("tellS");
				String zipS = (String) session.getAttribute("zipS");
				String addressS = (String) session.getAttribute("addressS");
				String addressdetailS = (String) session.getAttribute("addressdetailS");
				String errorCountS = (String) session.getAttribute("errorCountS");
				String searchCountS = (String) session.getAttribute("searchCountS");

				/* �������ʂ��\������Ă���ꍇ�̂ݍČ��� */
				if (errorCountS.equals("0") && searchCountS.equals("0")) {

					ArrayList<SearchBean> searchlistB = new ArrayList<SearchBean>();
					request.setAttribute("searchlist", searchlistB);
					int searchCountB = 0;
					request.setAttribute("searchCount", searchCountB);

					ArrayList<String> errorMessagesB = new ArrayList<String>();
					request.setAttribute("errorMessages", errorMessagesB);
					int errorCountB = 0;
					request.setAttribute("errorCount", errorCountB);

				} else {
					ArgumentBean ab = new ArgumentBean();

					ab.setIdfrom(idfromS);
					ab.setIdto(idtoS);
					ab.setName(nameS);
					ab.setAgefrom(agefromS);
					ab.setAgeto(agetoS);
					ab.setSex(sexS);
					ab.setJob(jobS);
					ab.setTell(tellS);
					ab.setZip(zipS);
					ab.setAddress(addressS);
					ab.setAddressdetail(addressdetailS);

					/* �G���[�`�F�b�N�����{ */
					SearchValidation svalidate = new SearchValidation();

					/* �G���[�`�F�b�N�̌��ʂ��擾 */
					ArrayList<String> errorMessagesB = svalidate.errorCheckS(ab);

					/* �G���[���b�Z�[�W�̐����擾 */
					int errorCountB = errorMessagesB.size();

					/* �f�[�^�x�[�X�ɑ΂��Č������������{ */
					SearchDao sdao = new SearchDao();

					/* �������ʂ��擾 */
					ArrayList<SearchBean> searchlistB = sdao.selectSearch(ab);

					/* �������ʂ̌������擾 */
					int searchCountB = searchlistB.size();

					/* �������ʂ�0���̏ꍇ�ɕ\�� */
					if (errorCountB == 0 && searchCountB == 0) {
						errorMessagesB.add("�Y������f�[�^�����݂��܂���B");
					}
					/* �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�Ɋi�[ */
					request.setAttribute("errorMessages", errorMessagesB);
					request.setAttribute("errorCount", errorCountB);

					/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
					request.setAttribute("searchlist", searchlistB);
					request.setAttribute("searchCount", searchCountB);
				}

				/* ������ʂ𕜌� */
				request.setAttribute("idfrom", idfromS);
				request.setAttribute("idto", idtoS);
				request.setAttribute("name", nameS);
				request.setAttribute("agefrom", agefromS);
				request.setAttribute("ageto", agetoS);
				request.setAttribute("sex", sexS);
				request.setAttribute("job", jobS);
				request.setAttribute("tell", tellS);
				request.setAttribute("zip", zipS);
				request.setAttribute("address", addressS);
				request.setAttribute("addressdetail", addressdetailS);

				/* ������ʂ֑J�� */
				request.getRequestDispatcher("./Search.jsp").forward(request, response);

			}
		} else if (Objects.equals(submitType, "�N���A")) {
			/* �G���[���b�Z�[�W�������� */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);

			/* �E�ƃ��X�g�������� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			/* �X�V���[�h�̏ꍇ�A�o�^ID�̓��͒l�͏��������Ȃ� */
			HttpSession session = request.getSession();
			String submitTypeS = (String) session.getAttribute("submitTypeS");
			if (Objects.equals(submitTypeS, "�ύX")) {
				request.setAttribute("readonly", "update");
				String id = request.getParameter("id");
				request.setAttribute("id", id);
			} else {
				request.setAttribute("readonly", "insert");
				request.setAttribute("id", "");
			}

			/* ���̓t�H�[���������� */
			request.setAttribute("name", "");
			request.setAttribute("age", "");
			request.setAttribute("sex", "male");
			request.setAttribute("job", "0");
			request.setAttribute("tell", "");
			request.setAttribute("zip", "");
			request.setAttribute("address", "");
			request.setAttribute("addressdetail", "");

			// �t�H���[�h�̎��s
			request.getRequestDispatcher("./Entry.jsp").forward(request, response);
		} else if (Objects.equals(submitType, "���~")) {

			/* �Z�b�V���������擾 */
			HttpSession session = request.getSession();
			String idfromS = (String) session.getAttribute("idfromS");
			String idtoS = (String) session.getAttribute("idtoS");
			String nameS = (String) session.getAttribute("nameS");
			String agefromS = (String) session.getAttribute("agefromS");
			String agetoS = (String) session.getAttribute("agetoS");
			String sexS = (String) session.getAttribute("sexS");
			String jobS = (String) session.getAttribute("jobS");
			String tellS = (String) session.getAttribute("tellS");
			String zipS = (String) session.getAttribute("zipS");
			String addressS = (String) session.getAttribute("addressS");
			String addressdetailS = (String) session.getAttribute("addressdetailS");
			String errorCountS = (String) session.getAttribute("errorCountS");
			String searchCountS = (String) session.getAttribute("searchCountS");

			/* �������ʂ��\������Ă���ꍇ�̂ݍČ��� */
			if (errorCountS.equals("0") && searchCountS.equals("0")) {

				ArrayList<SearchBean> searchlistB = new ArrayList<SearchBean>();
				request.setAttribute("searchlist", searchlistB);
				int searchCountB = 0;
				request.setAttribute("searchCount", searchCountB);

				ArrayList<String> errorMessagesB = new ArrayList<String>();
				request.setAttribute("errorMessages", errorMessagesB);
				int errorCountB = 0;
				request.setAttribute("errorCount", errorCountB);

			} else {
				ArgumentBean ab = new ArgumentBean();

				ab.setIdfrom(idfromS);
				ab.setIdto(idtoS);
				ab.setName(nameS);
				ab.setAgefrom(agefromS);
				ab.setAgeto(agetoS);
				ab.setSex(sexS);
				ab.setJob(jobS);
				ab.setTell(tellS);
				ab.setZip(zipS);
				ab.setAddress(addressS);
				ab.setAddressdetail(addressdetailS);

				/* �G���[�`�F�b�N�����{ */
				SearchValidation svalidate = new SearchValidation();

				/* �G���[�`�F�b�N�̌��ʂ��擾 */
				ArrayList<String> errorMessagesB = svalidate.errorCheckS(ab);

				/* �G���[���b�Z�[�W�̐����擾 */
				int errorCountB = errorMessagesB.size();

				/* �f�[�^�x�[�X�ɑ΂��Č������������{ */
				SearchDao sdao = new SearchDao();

				/* �������ʂ��擾 */
				ArrayList<SearchBean> searchlistB = sdao.selectSearch(ab);

				/* �������ʂ̌������擾 */
				int searchCountB = searchlistB.size();

				/* �������ʂ�0���̏ꍇ�ɕ\�� */
				if (errorCountB == 0 && searchCountB == 0) {
					errorMessagesB.add("�Y������f�[�^�����݂��܂���B");
				}
				/* �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�Ɋi�[ */
				request.setAttribute("errorMessages", errorMessagesB);
				request.setAttribute("errorCount", errorCountB);

				/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
				request.setAttribute("searchlist", searchlistB);
				request.setAttribute("searchCount", searchCountB);
			}

			/* ������ʂ𕜌� */
			request.setAttribute("idfrom", idfromS);
			request.setAttribute("idto", idtoS);
			request.setAttribute("name", nameS);
			request.setAttribute("agefrom", agefromS);
			request.setAttribute("ageto", agetoS);
			request.setAttribute("sex", sexS);
			request.setAttribute("job", jobS);
			request.setAttribute("tell", tellS);
			request.setAttribute("zip", zipS);
			request.setAttribute("address", addressS);
			request.setAttribute("addressdetail", addressdetailS);

			/* �E�ƃ��X�g���ĕ\�� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			/* ������ʂ֑J�� */
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
