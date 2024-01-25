package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.InsertBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.InsertDao;
import Dao.JobDao;
import Dao.SearchDao01;
import Dao.UpdateDao;
import Validation.InsertValidation;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/AddEntrySystemVersion2/Insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*ボタンの値で処理を分岐*/
		String submitType1 = request.getParameter("button1");
		String submitType2 = request.getParameter("button2");
		String submitType3 = request.getParameter("button3");
		
		if(submitType1 != null) {
			/* フォームから入力値を取得 */
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String job = request.getParameter("job");
		String tell = request.getParameter("tell");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("addressdetail");

		/* 入力値を格納するインスタンス */
		InsertBean ib = new InsertBean();

		/* フォーム内で入力された値を登録値としてセットする */
		ib.setId(id);
		ib.setName(name);
		ib.setAge(age);
		ib.setSex(sex);
		ib.setJob(job);
		ib.setTell(tell);
		ib.setZip(zip);
		ib.setAddress(address);
		ib.setAddressDetail(addressdetail);

		/* エラーチェックを実施 */
		InsertValidation ivalidate = new InsertValidation();

		/* エラーチェックの結果を取得 */
		ArrayList<String> errorMessages = ivalidate.errorCheckI(ib);

		if (errorMessages.size() != 0) {
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

			/* フォワードの実行 */
			request.getRequestDispatcher("./Entry.jsp").forward(request, response);
		}

		if (errorMessages.size() == 0) {

			/* 登録IDが重複しているデータがないか検索 */
			SearchDao01 sdao01 = new SearchDao01();

			/* 検索結果を取得 */
			ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);

			/* 登録処理を実施するか更新処理を実施するか判断する */
			if (searchlist01.size() == 0) {

				/* データベースに対して登録処理を実施 */
				InsertDao idao = new InsertDao();
				idao.insert(ib);

			} else {

				/* データベースに対して更新処理を実施 */
				UpdateDao udao = new UpdateDao();
				udao.update(ib);
			}

			/* 職業リストを再表示 */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);
			
			/*セッション情報を取得*/
			HttpSession session = request.getSession();
			ArrayList<SearchBean> searchlistS = (ArrayList<SearchBean>) session.getAttribute("searchlistS");
			ArrayList<String> errorMessagesS = (ArrayList<String>) session.getAttribute("errorMessagesS");
			String idfromS = (String) session.getAttribute("idfromS");
			String idtoS = (String) session.getAttribute("idtoS");
			String nameS = (String) session.getAttribute("nameS");
			String agefromS = (String) session.getAttribute("agefromS");
			String agetoS = (String) session.getAttribute("agetoS");
			String sexS = (String) session.getAttribute("sexS");
			String jobS = (String) session.getAttribute("jobS");
			String tellS = (String) session.getAttribute("tellS");
			String zipS = (String) session.getAttribute("zipS");
			String addressS = (String) session.getAttribute("addressS");
			String addressdetailS = (String) session.getAttribute("addressdetailS");
			
			/* 検索画面を復元 */
			request.setAttribute("idfrom", idfromS);
			request.setAttribute("idto", idtoS);
			request.setAttribute("name", nameS);
			request.setAttribute("agefrom", agefromS);
			request.setAttribute("ageto", agetoS);
			request.setAttribute("sex", sexS);
			request.setAttribute("job", jobS);
			request.setAttribute("tell", tellS);
			request.setAttribute("zip", zipS);
			request.setAttribute("address", addressS);
			request.setAttribute("addressdetail", addressdetailS);
			if(searchlistS == null) {
				ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
				request.setAttribute("searchlist", searchlist);
			}else {
				request.setAttribute("searchlist", searchlistS);
			}
			if(errorMessagesS == null) {
				ArrayList<String> errorMessagesSS = new ArrayList<String>();
				request.setAttribute("errorMessages", errorMessagesSS);
			}else {
				request.setAttribute("errorMessages", errorMessagesS);
			}
			
			
			/* 検索画面へ遷移*/
			request.getRequestDispatcher("./Search.jsp").forward(request, response);

		}
		}else if(submitType2 != null) {
			/* エラーメッセージを初期化 */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);

			/* 職業リストを初期化 */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);
			
			/* 入力フォームを初期化 */
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
		}else if(submitType3 != null) {
			/* 職業リストを再表示 */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);
			
			/*セッション情報を取得*/
			HttpSession session = request.getSession();
			ArrayList<SearchBean> searchlistS = (ArrayList<SearchBean>) session.getAttribute("searchlistS");
			ArrayList<String> errorMessagesS = (ArrayList<String>) session.getAttribute("errorMessagesS");
			String idfromS = request.getParameter("idfromS");
			String idtoS = request.getParameter("idtoS");
			String nameS = request.getParameter("nameS");
			String agefromS = request.getParameter("agefromS");
			String agetoS = request.getParameter("agetoS");
			String sexS = request.getParameter("sexS");
			String jobS = request.getParameter("jobS");
			String tellS = request.getParameter("tellS");
			String zipS = request.getParameter("zipS");
			String addressS = request.getParameter("addressS");
			String addressdetailS = request.getParameter("addressdetailS");
			
			/* 検索画面を復元 */
			request.setAttribute("idfrom", idfromS);
			request.setAttribute("idto", idtoS);
			request.setAttribute("name", nameS);
			request.setAttribute("agefrom", agefromS);
			request.setAttribute("ageto", agetoS);
			request.setAttribute("sex", sexS);
			request.setAttribute("job", jobS);
			request.setAttribute("tell", tellS);
			request.setAttribute("zip", zipS);
			request.setAttribute("address", addressS);
			request.setAttribute("addressdetail", addressdetailS);
			if(searchlistS == null) {
				ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
				request.setAttribute("searchlist", searchlist);
			}else {
				request.setAttribute("searchlist", searchlistS);
			}
			if(errorMessagesS == null) {
				ArrayList<String> errorMessagesSS = new ArrayList<String>();
				request.setAttribute("errorMessages", errorMessagesSS);
			}else {
				request.setAttribute("errorMessages", errorMessagesS);
			}
			
			
			/* 検索画面へ遷移*/
			request.getRequestDispatcher("./Search.jsp").forward(request, response);
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

}
