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

		/* 職業マスタから職業名を取得する */
		/* データベースに対して検索処理を実施 */
		JobDao jdao = new JobDao();

		/* 検索結果を取得 */
		ArrayList<JobBean> joblist = jdao.selectJob();

		/* 検索結果をリクエストスコープに格納 */
		request.setAttribute("joblist", joblist);

		// フォワードの実行
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
