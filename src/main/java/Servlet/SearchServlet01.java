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
		
		// 検索値を格納するインスタンス
		ArgumentBean ab = new ArgumentBean();
					
		// フォーム内で入力された値を検索値としてセットする
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
		
		/*データベースに対して検索処理を実施*/
		SearchDao sdao = new SearchDao();
		
		/*検索結果を取得*/
		ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);
		
		/*検索結果をリクエストスコープに格納*/
		request.setAttribute("searchlist",searchlist);
		/*検索条件を保持*/
		request.setAttribute("idfrom",idfrom);
		request.setAttribute("idto",idto);
		request.setAttribute("name", name);
		request.setAttribute("agefrom",agefrom);
		request.setAttribute("ageto",ageto);
		request.setAttribute("tell",tell);
		request.setAttribute("zip",zip);
		request.setAttribute("address",address);
		request.setAttribute("addressdetail",addressdetail);
		
		/*職業リストを再表示*/
		JobDao jdao = new JobDao();
		ArrayList<JobBean> joblist = jdao.selectJob();
		request.setAttribute("joblist",joblist);
		
		//フォワードの実行
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
