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
		
		//エラーメッセージ
		ArrayList<String> errorMessages = new ArrayList<String>();
		String msg = "は必ず入力してください。";
		
		if(id == "") {
			errorMessages.add("登録ID" + msg);
		}else if(name == "") {
			errorMessages.add("氏名" + msg);
		}else if(age == "") {
			errorMessages.add("年齢" + msg);
		}else if(tell == "") {
			errorMessages.add("電話番号" + msg);
		}else if(zip == "") {
			errorMessages.add("郵便番号" + msg);
		}else if(zip == "") {
			errorMessages.add("郵便番号" + msg);
		}else if(address == "") {
			errorMessages.add("住所" + msg);
		}
		
		if(errorMessages != null || errorMessages.size() != 0) {
			/* エラーメッセージをリクエストスコープに格納 */
			request.setAttribute("errorMessages", errorMessages);
			
			/* 職業リストを再表示 */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);
			
			// フォワードの実行
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
