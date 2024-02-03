package Servlet;

import java.io.IOException;
import java.util.ArrayList;

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

		if (submitType.equals("����")) {

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
			int errorCount = errorMessages.size();

			/* �f�[�^�x�[�X�ɑ΂��Č������������{ */
			SearchDao sdao = new SearchDao();

			/* �������ʂ��擾 */
			ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

			/* �������ʂ̌������擾 */
			int searchCount = searchlist.size();

			/* �������ʂ�0���̏ꍇ�ɕ\�� */
			if (errorCount == 0 && searchCount == 0) {
				errorMessages.add("�Y������f�[�^�����݂��܂���B");
			}

			/* �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�Ɋi�[ */
			request.setAttribute("errorMessages", errorMessages);
			request.setAttribute("errorCount", errorCount);

			/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
			request.setAttribute("searchlist", searchlist);
			request.setAttribute("searchCount", searchCount);

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

		} else if (submitType.equals("�N���A")) {
			/* �������ʂ������� */
			ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
			request.setAttribute("searchlist", searchlist);
			int searchCount = 0;
			request.setAttribute("searchCount", searchCount);

			/* �G���[���b�Z�[�W�������� */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);
			int errorCount = 0;
			request.setAttribute("errorCount", errorCount);

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

			/* �E�ƃ��X�g���ĕ\�� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			/* �t�H���[�h�̎��s */
			request.getRequestDispatcher("./Search.jsp").forward(request, response);
		} else if (submitType.equals("�V�K")) {

			/* ������ʂœ��͂���Ă����l���擾 */
			String idfromS = request.getParameter("idfrom");
			String idtoS = request.getParameter("idto");
			String nameS = request.getParameter("name");
			String agefromS = request.getParameter("agefrom");
			String agetoS = request.getParameter("ageto");
			String sexS = request.getParameter("sex");
			String jobS = request.getParameter("job");
			String tellS = request.getParameter("tell");
			String zipS = request.getParameter("zip");
			String addressS = request.getParameter("address");
			String addressdetailS = request.getParameter("addressdetail");

			/* �������ʁE�G���[�̏����擾 */
			String errorCountS = request.getParameter("errorCount");
			String searchCountS = request.getParameter("searchCount");

			/* ������ʂ̏����Z�b�V�����Ɋi�[(��ʕ����p) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfromS);
			session.setAttribute("idtoS", idtoS);
			session.setAttribute("nameS", nameS);
			session.setAttribute("agefromS", agefromS);
			session.setAttribute("agetoS", agetoS);
			session.setAttribute("sexS", sexS);
			session.setAttribute("jobS", jobS);
			session.setAttribute("tellS", tellS);
			session.setAttribute("zipS", zipS);
			session.setAttribute("addressS", addressS);
			session.setAttribute("addressdetailS", addressdetailS);
			session.setAttribute("errorCountS", errorCountS);
			session.setAttribute("searchCountS", searchCountS);

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
			request.setAttribute("readonly", "false");

			// �t�H���[�h�̎��s
			request.getRequestDispatcher("./Entry.jsp").forward(request, response);
		}else if(submitType.equals("�ύX")) {
			/* �`�F�b�N�{�b�N�X�ɂĎw�肳�ꂽ�o�^ID�����ƂɍX�V��ʂ֑J�� */
			String[] selectedIdLists = request.getParameterValues("check");
			
			/* ������ʂœ��͂���Ă����l���擾 */
			String idfromS = request.getParameter("idfrom");
			String idtoS = request.getParameter("idto");
			String nameS = request.getParameter("name");
			String agefromS = request.getParameter("agefrom");
			String agetoS = request.getParameter("ageto");
			String sexS = request.getParameter("sex");
			String jobS = request.getParameter("job");
			String tellS = request.getParameter("tell");
			String zipS = request.getParameter("zip");
			String addressS = request.getParameter("address");
			String addressdetailS = request.getParameter("addressdetail");

			/* �������ʁE�G���[���b�Z�[�W�̌����擾 */
			String errorCountS = request.getParameter("errorCount");
			String searchCountS = request.getParameter("searchCount");

			/* ������ʂ̏����Z�b�V�����Ɋi�[(��ʕ����p) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfromS);
			session.setAttribute("idtoS", idtoS);
			session.setAttribute("nameS", nameS);
			session.setAttribute("agefromS", agefromS);
			session.setAttribute("agetoS", agetoS);
			session.setAttribute("sexS", sexS);
			session.setAttribute("jobS", jobS);
			session.setAttribute("tellS", tellS);
			session.setAttribute("zipS", zipS);
			session.setAttribute("addressS", addressS);
			session.setAttribute("addressdetailS", addressdetailS);
			session.setAttribute("errorCountS", errorCountS);
			session.setAttribute("searchCountS", searchCountS);
			
			/*�`�F�b�N����Ă���s�������A�܂��͕����̏ꍇ�A�G���[���b�Z�[�W��\��*/
			if(selectedIdLists == null || selectedIdLists.length > 1) {
				ArrayList<String> errorMessages = new ArrayList<String>();
				if(selectedIdLists == null) {
					errorMessages.add("�Ώۃf�[�^���I������Ă��܂���B\n�Z�����X�g�ɂđΏۂ�I�����Ă��������B");
				}else if(selectedIdLists.length > 1) {
					errorMessages.add("�Ώۃf�[�^�������s�I������Ă��܂��B\n�Z�����X�g�ɂđΏۂ�1�s�̂ݑI�����Ă��������B");
				}
				
				request.setAttribute("errorMessages", errorMessages);
				int errorCount = errorMessages.size();
				request.setAttribute("errorCount", errorCount);
				
				if (!searchCountS.equals("0")) {
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

					/* �f�[�^�x�[�X�ɑ΂��Č������������{ */
					SearchDao sdao = new SearchDao();

					/* �������ʂ��擾 */
					ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

					/* �������ʂ̌������擾 */
					int searchCount = searchlist.size();

					/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
					request.setAttribute("searchlist", searchlist);
					request.setAttribute("searchCount", searchCount);
				}else {
					ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
					request.setAttribute("searchlist", searchlist);
					int searchCount = 0;
					request.setAttribute("searchCount", searchCount);
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
			}else {

				String selectedId = null;
				for(String id : selectedIdLists) {
					selectedId = id;
				}
				/* ���͒l���i�[����C���X�^���X */
				InsertBean ib = new InsertBean();

				/* �t�H�[�����œ��͂��ꂽ�l��o�^�l�Ƃ��ăZ�b�g���� */
				ib.setId(selectedId);
				
				/* �o�^ID���d�����Ă���f�[�^���Ȃ������� */
				SearchDao01 sdao01 = new SearchDao01();

				/* �������ʂ��擾 */
				ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);
				
				/* �����l���擾 */
				String name = searchlist01.get(0).getName();
				String age = String.valueOf(searchlist01.get(0).getAge());
				String sex = searchlist01.get(0).getSex();
				String job = searchlist01.get(0).getJob();
				String tell = searchlist01.get(0).getTell();
				String zip = searchlist01.get(0).getZip();
				String address = searchlist01.get(0).getAddress();
				String addressdetail = searchlist01.get(0).getAddressDetail();
				
				/* ���̓t�H�[���Ɍ��������l���Z�b�g */
				request.setAttribute("id", selectedId);
				request.setAttribute("name", name);
				request.setAttribute("age", age);
				request.setAttribute("sex", sex);
				request.setAttribute("job", job);
				request.setAttribute("tell", tell);
				request.setAttribute("zip", zip);
				request.setAttribute("address", address);
				request.setAttribute("addressdetail", addressdetail);
				/*�X�V���[�h�ؑւ��̂��߁A�o�^ID�̓��͗���ҏW�s�Ƃ���*/
				request.setAttribute("readonly", "true");
				
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
