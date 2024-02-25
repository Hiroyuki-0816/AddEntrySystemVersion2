package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.JobBean;
import Bean.SearchBean;
import Dao.JobDao;

/**
 * Servlet implementation class test
 */
@WebServlet("/AddEntrySystemVersion2/")
public class InitialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/* 検索結果を格納するリスト */
		ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
		request.setAttribute("searchlist", searchlist);

		/* エラーメッセージを格納するリスト */
		ArrayList<String> errorMessages = new ArrayList<String>();
		request.setAttribute("errorMessages", errorMessages);

		/* 職業リストを職業マスタから生成 */
		JobDao jdao = new JobDao();
		ArrayList<JobBean> joblist = jdao.selectJob();
		request.setAttribute("joblist", joblist);

		/* 検索条件の初期状態を設定 */
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

		// フォワードの実行
		request.getRequestDispatcher("./Search.jsp").forward(request, response);

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
