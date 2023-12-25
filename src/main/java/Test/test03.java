package Test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TestBean.JobBean;
import TestBean.SearchBean;
import TestDao.JobDao;
import TestDao.SearchDao;

/**
 * Servlet implementation class test03
 */
@WebServlet("/test03")
public class test03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*�f�[�^�x�[�X�ɑ΂��Č������������{*/
		JobDao jdao = new JobDao();
		
		/*�������ʂ��擾*/
		ArrayList<JobBean> joblist = jdao.selectJob();
		
		/*�������ʂ����N�G�X�g�X�R�[�v�Ɋi�[*/
		request.setAttribute("joblist",joblist);
		
		request.setCharacterEncoding("UTF-8");
		int agefrom = Integer.parseInt(request.getParameter("agefrom"));
		int ageto = Integer.parseInt(request.getParameter("ageto"));
		String name = request.getParameter("name");
		String tell = request.getParameter("tell");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("addressdetail");
		
		// Bean�N���X�̃C���X�^���X
		SearchBean sb = new SearchBean();
					
		// �t�H�[�����œ��͂��ꂽ�l�������l�Ƃ��ăZ�b�g����
		sb.setAge(agefrom);
		sb.setAge(ageto);
		sb.setName(name);
		sb.setTell(tell);
		sb.setZip(zip);
		sb.setAddress(address);
		sb.setAddressDetail(addressdetail);
		
		/*�f�[�^�x�[�X�ɑ΂��Č������������{*/
		SearchDao sdao = new SearchDao();
		
		/*�������ʂ��擾*/
		ArrayList<SearchBean> searchlist = sdao.selectSearch(sb);
		
		/*�������ʂ����N�G�X�g�X�R�[�v�Ɋi�[*/
		request.setAttribute("searchlist",searchlist);
		
		//�t�H���[�h�̎��s
		request.getRequestDispatcher("./test03.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
