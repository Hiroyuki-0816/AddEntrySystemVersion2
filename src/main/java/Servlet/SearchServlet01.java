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
import Dao.JobDao;
import Dao.SearchDao;
import Dao.SearchDao01;
import Validation.SearchValidation;

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
		/* �{�^���̒l�ŏ����𕪊� */
		String submitType = request.getParameter("button");

		/* �����N����J�ڂ����ꍇ */
		String submitId = request.getParameter("submitId");
		/* �t�H�[�����猟���������擾 */
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
		/* �������ʁE�G���[�̏����擾 */
		String errorCount = request.getParameter("errorCount");
		String searchCount = request.getParameter("searchCount");

		if (Objects.equals(submitType, "����")) {

			/* �����l���i�[����C���X�^���X */
			ArgumentBean ab = new ArgumentBean();

			/* �t�H�[�����œ��͂��ꂽ�l�������l�Ƃ��ăZ�b�g���� */
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

			/* �G���[�`�F�b�N�����{ */
			SearchValidation svalidate = new SearchValidation();

			/* �G���[�`�F�b�N�̌��ʂ��擾 */
			ArrayList<String> errorMessages = svalidate.errorCheckS(ab);

			/* �G���[���b�Z�[�W�̐����擾 */
			int errorCountS = errorMessages.size();

			/* �f�[�^�x�[�X�ɑ΂��Č������������{ */
			SearchDao sdao = new SearchDao();

			/* �������ʂ��擾 */
			ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

			/* �������ʂ̌������擾 */
			int searchCountS = searchlist.size();

			/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
			request.setAttribute("searchlist", searchlist);
			request.setAttribute("searchCount", searchCountS);

			/* �������ʂ�0���̏ꍇ�ɕ\�� */
			if (errorCountS == 0 && searchCountS == 0) {
				errorMessages.add("�Y������f�[�^�����݂��܂���B");
			}

			/* �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�Ɋi�[ */
			request.setAttribute("errorMessages", errorMessages);
			request.setAttribute("errorCount", errorCountS);

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

			/* �t�H���[�h�̎��s */
			request.getRequestDispatcher("./Search.jsp").forward(request, response);

		} else if (Objects.equals(submitType, "�N���A")) {
			/* �������ʂ������� */
			ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
			request.setAttribute("searchlist", searchlist);

			/* �G���[���b�Z�[�W�������� */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);

			/* ���������������� */
			request.setAttribute("idfrom", "");
			request.setAttribute("idto", "");
			request.setAttribute("name", "");
			request.setAttribute("agefrom", "");
			request.setAttribute("ageto", "");
			request.setAttribute("sex", "both");
			request.setAttribute("job", "0");
			request.setAttribute("tell", "");
			request.setAttribute("zip", "");
			request.setAttribute("address", "");
			request.setAttribute("addressdetail", "");
			request.setAttribute("searchCount", 0);
			request.setAttribute("errorCount", 0);

			/* �E�ƃ��X�g���ĕ\�� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			/* �t�H���[�h�̎��s */
			request.getRequestDispatcher("./Search.jsp").forward(request, response);
		} else if (Objects.equals(submitType, "�V�K")) {
			/* ������ʂ̏����Z�b�V�����Ɋi�[(��ʕ����p) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfrom);
			session.setAttribute("idtoS", idto);
			session.setAttribute("nameS", name);
			session.setAttribute("agefromS", agefrom);
			session.setAttribute("agetoS", ageto);
			session.setAttribute("sexS", sex);
			session.setAttribute("jobS", job);
			session.setAttribute("tellS", tell);
			session.setAttribute("zipS", zip);
			session.setAttribute("addressS", address);
			session.setAttribute("addressdetailS", addressdetail);
			session.setAttribute("errorCountS", errorCount);
			session.setAttribute("searchCountS", searchCount);
			session.setAttribute("submitTypeS", submitType);

			/* �G���[���b�Z�[�W���i�[���郊�X�g */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);

			/* �E�ƃ��X�g��E�ƃ}�X�^���琶�� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			/* ���̓t�H�[���̏�����Ԃ�ݒ� */
			request.setAttribute("id", "");
			request.setAttribute("name", "");
			request.setAttribute("age", "");
			request.setAttribute("sex", "male");
			request.setAttribute("job", "0");
			request.setAttribute("tell", "");
			request.setAttribute("zip", "");
			request.setAttribute("address", "");
			request.setAttribute("addressdetail", "");
			/* �V�K���[�h�Ȃ̂ŁA�o�^ID�̕ҏW���Ƃ��� */
			request.setAttribute("readonly", "insert");

			// �t�H���[�h�̎��s
			request.getRequestDispatcher("./Entry.jsp").forward(request, response);
		} else if (Objects.equals(submitType, "�ύX")) {

			/* ������ʂ̏����Z�b�V�����Ɋi�[(��ʕ����p) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfrom);
			session.setAttribute("idtoS", idto);
			session.setAttribute("nameS", name);
			session.setAttribute("agefromS", agefrom);
			session.setAttribute("agetoS", ageto);
			session.setAttribute("sexS", sex);
			session.setAttribute("jobS", job);
			session.setAttribute("tellS", tell);
			session.setAttribute("zipS", zip);
			session.setAttribute("addressS", address);
			session.setAttribute("addressdetailS", addressdetail);
			session.setAttribute("errorCountS", errorCount);
			session.setAttribute("searchCountS", searchCount);
			session.setAttribute("submitTypeS", submitType);

			/* �`�F�b�N�{�b�N�X�ɂĎw�肳�ꂽ�o�^ID�����ƂɍX�V��ʂ֑J�� */
			String[] selectedIdLists = request.getParameterValues("check");

			/* �`�F�b�N����Ă���s�������A�܂��͕����̏ꍇ�A�G���[���b�Z�[�W��\�� */
			if (selectedIdLists == null || selectedIdLists.length > 1) {
				ArrayList<String> errorMessages = new ArrayList<String>();
				if (selectedIdLists == null) {
					errorMessages.add("�Ώۃf�[�^���I������Ă��܂���B\n�Z�����X�g�ɂđΏۂ�I�����Ă��������B");
				} else if (selectedIdLists.length > 1) {
					errorMessages.add("�Ώۃf�[�^�������s�I������Ă��܂��B\n�Z�����X�g�ɂđΏۂ�1�s�̂ݑI�����Ă��������B");
				}

				request.setAttribute("errorMessages", errorMessages);
				request.setAttribute("errorCount", 1);

				if (!searchCount.equals("0")) {
					ArgumentBean ab = new ArgumentBean();

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

					/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
					request.setAttribute("searchlist", searchlist);
					request.setAttribute("searchCount", searchCount);
				} else {
					ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
					request.setAttribute("searchlist", searchlist);
					request.setAttribute("searchCount", 0);
				}

				/* ������ʂ𕜌� */
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

				/* ������ʂ֑J�� */
				request.getRequestDispatcher("./Search.jsp").forward(request, response);
			} else {

				String selectedId = null;
				for (String id : selectedIdLists) {
					selectedId = id;
				}
				/* ���͒l���i�[����C���X�^���X */
				InsertBean ib = new InsertBean();

				/* �t�H�[�����œ��͂��ꂽ�l��o�^�l�Ƃ��ăZ�b�g���� */
				ib.setId(selectedId);

				/* �o�^ID�������Ƃ��ăe�[�u����茟�� */
				SearchDao01 sdao01 = new SearchDao01();

				/* �������ʂ��擾 */
				ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);

				/* �����l���擾 */
				String nameE = searchlist01.get(0).getName();
				String ageE = String.valueOf(searchlist01.get(0).getAge());
				String sexE = searchlist01.get(0).getSex();
				String jobE = searchlist01.get(0).getJob();
				String tellE = searchlist01.get(0).getTell();
				String zipE = searchlist01.get(0).getZip();
				String addressE = searchlist01.get(0).getAddress();
				String addressdetailE = searchlist01.get(0).getAddressDetail();

				/* ���̓t�H�[���Ɍ��������l���Z�b�g */
				request.setAttribute("id", selectedId);
				request.setAttribute("name", nameE);
				request.setAttribute("age", ageE);
				request.setAttribute("sex", sexE);
				request.setAttribute("job", jobE);
				request.setAttribute("tell", tellE);
				request.setAttribute("zip", zipE);
				request.setAttribute("address", addressE);
				request.setAttribute("addressdetail", addressdetailE);
				/* �ύX���[�h�Ȃ̂ŁA�o�^ID�̕ҏW��s�Ƃ��� */
				request.setAttribute("readonly", "update");

				/* �G���[���b�Z�[�W���i�[���郊�X�g */
				ArrayList<String> errorMessages = new ArrayList<String>();
				request.setAttribute("errorMessages", errorMessages);

				/* �E�ƃ��X�g��E�ƃ}�X�^���琶�� */
				JobDao jdao = new JobDao();
				ArrayList<JobBean> joblist = jdao.selectJob();
				request.setAttribute("joblist", joblist);

				// �t�H���[�h�̎��s
				request.getRequestDispatcher("./Entry.jsp").forward(request, response);

			}
		} else if (submitId != null) {

			/* ������ʂ̏����Z�b�V�����Ɋi�[(��ʕ����p) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfrom);
			session.setAttribute("idtoS", idto);
			session.setAttribute("nameS", name);
			session.setAttribute("agefromS", agefrom);
			session.setAttribute("agetoS", ageto);
			session.setAttribute("sexS", sex);
			session.setAttribute("jobS", job);
			session.setAttribute("tellS", tell);
			session.setAttribute("zipS", zip);
			session.setAttribute("addressS", address);
			session.setAttribute("addressdetailS", addressdetail);
			session.setAttribute("errorCountS", errorCount);
			session.setAttribute("searchCountS", searchCount);
			session.setAttribute("submitTypeS", "�ύX");

			/* ���͒l���i�[����C���X�^���X */
			InsertBean ib = new InsertBean();

			/* �����N��ID��o�^�l�Ƃ��ăZ�b�g���� */
			ib.setId(submitId);

			/* �o�^ID�������Ƃ��ăe�[�u����茟�� */
			SearchDao01 sdao01 = new SearchDao01();

			/* �������ʂ��擾 */
			ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);

			/* �����l���擾 */
			String nameE = searchlist01.get(0).getName();
			String ageE = String.valueOf(searchlist01.get(0).getAge());
			String sexE = searchlist01.get(0).getSex();
			String jobE = searchlist01.get(0).getJob();
			String tellE = searchlist01.get(0).getTell();
			String zipE = searchlist01.get(0).getZip();
			String addressE = searchlist01.get(0).getAddress();
			String addressdetailE = searchlist01.get(0).getAddressDetail();

			/* ���̓t�H�[���Ɍ��������l���Z�b�g */
			request.setAttribute("id", submitId);
			request.setAttribute("name", nameE);
			request.setAttribute("age", ageE);
			request.setAttribute("sex", sexE);
			request.setAttribute("job", jobE);
			request.setAttribute("tell", tellE);
			request.setAttribute("zip", zipE);
			request.setAttribute("address", addressE);
			request.setAttribute("addressdetail", addressdetailE);
			/* �ύX���[�h�Ȃ̂ŁA�o�^ID�̕ҏW��s�Ƃ��� */
			request.setAttribute("readonly", "update");

			/* �G���[���b�Z�[�W���i�[���郊�X�g */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);

			/* �E�ƃ��X�g��E�ƃ}�X�^���琶�� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			// �t�H���[�h�̎��s
			request.getRequestDispatcher("./Entry.jsp").forward(request, response);
		}

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
