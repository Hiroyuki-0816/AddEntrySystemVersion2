package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.ArgumentBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.JobDao;
import Dao.SearchDao;
import Validation.SearchValidation;

/**
 * Servlet implementation class test03
 */
@WebServlet("/AddEntrySystemVersion2/Search01")
public class SearchServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*ボタンの値で処理を分岐*/
		String submitType1 = request.getParameter("button1");
		String submitType2 = request.getParameter("button2");
		String submitType3 = request.getParameter("button3");
		
		if(submitType1 != null) {
			
			/* フォームから検索条件を取得 */
			request.setCharacterEncoding("UTF-8");
			String idfrom = request.getParameter("idfrom");
			String idto = request.getParameter("idto");
			String name = request.getParameter("name");
			String agefrom = request.getParameter("agefrom");
			String ageto = request.getParameter("ageto");
			String sex = request.getParameter("sex");
			String job = request.getParameter("job");
			String tell = request.getParameter("tell");
			String zip = request.getParameter("zip");
			String address = request.getParameter("address");
			String addressdetail = request.getParameter("addressdetail");
			
			/* 検索値を格納するインスタンス */
		ArgumentBean ab = new ArgumentBean();

		/* フォーム内で入力された値を検索値としてセットする */
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

		/* エラーチェックを実施 */
		SearchValidation svalidate = new SearchValidation();

		/* エラーチェックの結果を取得 */
		ArrayList<String> errorMessages = svalidate.errorCheckS(ab);

		/* データベースに対して検索処理を実施 */
		SearchDao sdao = new SearchDao();

		/* 検索結果を取得 */
		ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

		/* 検索結果が0件の場合に表示 */
		if (errorMessages.size() == 0 && searchlist.size() == 0) {
			errorMessages.add("該当するデータが存在しません。");
		}
		
		/* エラーメッセージをリクエストスコープに格納 */
		request.setAttribute("errorMessages", errorMessages);

		/* 検索結果をリクエストスコープに格納 */
		request.setAttribute("searchlist", searchlist);

		/* 検索条件を保持 */
		request.setAttribute("idfrom", idfrom);
		request.setAttribute("idto", idto);
		request.setAttribute("name", name);
		request.setAttribute("agefrom", agefrom);
		request.setAttribute("ageto", ageto);
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
		request.getRequestDispatcher("./Search.jsp").forward(request, response);
		
		} else if(submitType2 != null){
			/* 検索結果を初期化 */
			ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
			request.setAttribute("searchlist", searchlist);
			
			/* エラーメッセージを初期化 */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);
			
			/* 検索条件を初期化 */
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
			
			/* 職業リストを再表示 */
		JobDao jdao = new JobDao();
		ArrayList<JobBean> joblist = jdao.selectJob();
		request.setAttribute("joblist", joblist);

		/* フォワードの実行 */
		request.getRequestDispatcher("./Search.jsp").forward(request, response);
		} else if(submitType3 != null) {
			
			/*検索画面で入力されていた値を取得*/
			request.setCharacterEncoding("UTF-8");
			String idfromS = request.getParameter("idfrom");
			String idtoS = request.getParameter("idto");
			String nameS = request.getParameter("name");
			String agefromS = request.getParameter("agefrom");
			String agetoS = request.getParameter("ageto");
			String sexS = request.getParameter("sex");
			String jobS = request.getParameter("job");
			String tellS = request.getParameter("tell");
			String zipS = request.getParameter("zip");
			String addressS = request.getParameter("address");
			String addressdetailS = request.getParameter("addressdetail");
			ArrayList<SearchBean> searchlistS = (ArrayList<SearchBean>) request.getAttribute("searchlist");
			ArrayList<String> errorMessagesS = (ArrayList<String>) request.getAttribute("errorMessages");
			
			/*検索画面のセッション情報を取得(画面復元用)*/
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfromS);
			session.setAttribute("idtoS", idtoS);
			session.setAttribute("nameS", nameS);
			session.setAttribute("agefromS", agefromS);
			session.setAttribute("agetoS", agetoS);
			session.setAttribute("sexS", sexS);
			session.setAttribute("jobS", jobS);
			session.setAttribute("tellS", tellS);
			session.setAttribute("zipS", zipS);
			session.setAttribute("addressS",addressS);
			session.setAttribute("addressdetailS", addressdetailS);
			session.setAttribute("searchlistS", searchlistS);
			session.setAttribute("errorMessagesS", errorMessagesS);
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
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
