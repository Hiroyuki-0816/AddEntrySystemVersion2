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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/* �E�ƃ}�X�^����E�Ɩ����擾���� */
		/* �f�[�^�x�[�X�ɑ΂��Č������������{ */
		JobDao jdao = new JobDao();

		/* �������ʂ��擾 */
		ArrayList<JobBean> joblist = jdao.selectJob();

		/* �������ʂ����N�G�X�g�X�R�[�v�Ɋi�[ */
		request.setAttribute("joblist", joblist);

		// �t�H���[�h�̎��s
		request.getRequestDispatcher("./test01.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
