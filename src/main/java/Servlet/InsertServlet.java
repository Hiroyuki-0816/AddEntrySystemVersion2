package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.JobBean;
import Dao.JobDao;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/AddEntrySystemVersion2/Insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
		
		//�G���[���b�Z�[�W
		ArrayList<String> errorMessages = new ArrayList<String>();
		String msg = "�͕K�����͂��Ă��������B";
		
		if(id == "") {
			errorMessages.add("�o�^ID" + msg);
		}else if(name == "") {
			errorMessages.add("����" + msg);
		}else if(age == "") {
			errorMessages.add("�N��" + msg);
		}else if(tell == "") {
			errorMessages.add("�d�b�ԍ�" + msg);
		}else if(zip == "") {
			errorMessages.add("�X�֔ԍ�" + msg);
		}else if(zip == "") {
			errorMessages.add("�X�֔ԍ�" + msg);
		}else if(address == "") {
			errorMessages.add("�Z��" + msg);
		}
		
		if(errorMessages != null || errorMessages.size() != 0) {
			/* �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�Ɋi�[ */
			request.setAttribute("errorMessages", errorMessages);
			
			/* �E�ƃ��X�g���ĕ\�� */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);
			
			// �t�H���[�h�̎��s
			request.getRequestDispatcher("./EntryError.jsp").forward(request, response);
		}
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

}
