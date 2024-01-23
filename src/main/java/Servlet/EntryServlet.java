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
 * Servlet implementation class test01
 */
@WebServlet("/AddEntrySystemVersion2/Entry")
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		// �t�H���[�h�̎��s
		request.getRequestDispatcher("./Entry.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
