package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ArgumentBean;
import Bean.InsertBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.JobDao;
import Dao.SearchDao;
import Dao.SearchDao01;

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
			/* 入力項目を保持 */
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("age", age);
			request.setAttribute("sex", sex);
			request.setAttribute("job", job);
			request.setAttribute("tell", tell);
			request.setAttribute("zip", zip);
			request.setAttribute("address", address);
			request.setAttribute("addressdetail", addressdetail);
			
			/* 職業リストを再表示 */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);
			
			// フォワードの実行
			request.getRequestDispatcher("./EntryError.jsp").forward(request, response);
		}
		
		        // 入力値を格納するインスタンス
				InsertBean ib = new InsertBean();

				// フォーム内で入力された値を検索値としてセットする
			    ib.setId(Integer.parseInt(id));
				ib.setName(name);
				ib.setAge(Integer.parseInt(age));
				ib.setSex(sex);
				ib.setJob(job);
				ib.setTell(tell);
				ib.setZip(zip);
				ib.setAddress(address);
				ib.setAddressDetail(addressdetail);
				
		/* 登録IDが重複しているデータがないか検索 */
		SearchDao01 sdao01 = new SearchDao01();

		/* 検索結果を取得 */
		ArrayList<SearchBean> searchlist = sdao01.insertSearch(ib);
		
		/*登録処理を実施するか更新処理を実施するか判断する*/
		if(searchlist.size() == 0) {
			System.out.println("true");
		}else {
			System.out.println("false");
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
