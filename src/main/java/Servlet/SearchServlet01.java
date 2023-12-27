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
		/*データベースに対して検索処理を実施*/
		JobDao jdao = new JobDao();
		
		/*検索結果を取得*/
		ArrayList<JobBean> joblist = jdao.selectJob();
		
		/*検索結果をリクエストスコープに格納*/
		request.setAttribute("joblist",joblist);
		
		request.setCharacterEncoding("UTF-8");
		int idfrom = Integer.parseInt(request.getParameter("idfrom"));
		int idto = Integer.parseInt(request.getParameter("idto"));
		String name = request.getParameter("name");
		int agefrom = Integer.parseInt(request.getParameter("agefrom"));
		int ageto = Integer.parseInt(request.getParameter("ageto"));
		String tell = request.getParameter("tell");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("addressdetail");
		
		// Beanクラスのインスタンス
		ArgumentBean ab = new ArgumentBean();
					
		// フォーム内で入力された値を検索値としてセットする
		ab.setIdfrom(idfrom);
		ab.setIdto(idto);
		ab.setName(name);
		ab.setAgefrom(agefrom);
		ab.setAgeto(ageto);
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
