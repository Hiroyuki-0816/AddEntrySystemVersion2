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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String idfrom = String.valueOf(request.getParameter("idfrom"));
		String idto = String.valueOf(request.getParameter("idto"));
		String name = request.getParameter("name");
		String agefrom = String.valueOf(request.getParameter("agefrom"));
		String ageto = String.valueOf(request.getParameter("ageto"));
		String sex = request.getParameter("sex");
		String job = request.getParameter("job");
		String tell = request.getParameter("tell");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("addressdetail");
		
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
		
		/*�f�[�^�x�[�X�ɑ΂��Č������������{*/
		SearchDao sdao = new SearchDao();
		
		/*�������ʂ��擾*/
		ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);
		
		/*�������ʂ����N�G�X�g�X�R�[�v�Ɋi�[*/
		request.setAttribute("searchlist",searchlist);
		/*����������ێ�*/
		request.setAttribute("idfrom",idfrom);
		request.setAttribute("idto",idto);
		request.setAttribute("name", name);
		request.setAttribute("agefrom",agefrom);
		request.setAttribute("ageto",ageto);
		request.setAttribute("tell",tell);
		request.setAttribute("zip",zip);
		request.setAttribute("address",address);
		request.setAttribute("addressdetail",addressdetail);
		
		/*�E�ƃ��X�g���ĕ\��*/
		JobDao jdao = new JobDao();
		ArrayList<JobBean> joblist = jdao.selectJob();
		request.setAttribute("joblist",joblist);
		
		//�t�H���[�h�̎��s
		request.getRequestDispatcher("./test03.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
