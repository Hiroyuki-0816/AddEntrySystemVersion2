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
		/* エラーメッセージを格納するリスト */
		ArrayList<String> errorMessages = new ArrayList<String>();
		request.setAttribute("errorMessages", errorMessages);

		/* 職業リストを職業マスタから生成 */
		JobDao jdao = new JobDao();
		ArrayList<JobBean> joblist = jdao.selectJob();
		request.setAttribute("joblist", joblist);
		
		/* 入力フォームの初期状態を設定 */
		request.setAttribute("id", "");
		request.setAttribute("name", "");
		request.setAttribute("age", "");
		request.setAttribute("sex", "male");
		request.setAttribute("job", "0");
		request.setAttribute("tell", "");
		request.setAttribute("zip", "");
		request.setAttribute("address", "");
		request.setAttribute("addressdetail", "");

		// フォワードの実行
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
