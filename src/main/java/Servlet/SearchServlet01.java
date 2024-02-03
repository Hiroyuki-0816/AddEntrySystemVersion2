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
import Bean.InsertBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.JobDao;
import Dao.SearchDao;
import Dao.SearchDao01;
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
		/* ボタンの値で処理を分岐 */
		String submitType = request.getParameter("button");

		if (submitType.equals("検索")) {

			/* フォームから検索条件を取得 */
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

			/* エラーメッセージの数を取得 */
			int errorCount = errorMessages.size();

			/* データベースに対して検索処理を実施 */
			SearchDao sdao = new SearchDao();

			/* 検索結果を取得 */
			ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

			/* 検索結果の件数を取得 */
			int searchCount = searchlist.size();

			/* 検索結果が0件の場合に表示 */
			if (errorCount == 0 && searchCount == 0) {
				errorMessages.add("該当するデータが存在しません。");
			}

			/* エラーメッセージをリクエストスコープに格納 */
			request.setAttribute("errorMessages", errorMessages);
			request.setAttribute("errorCount", errorCount);

			/* 検索結果をリクエストスコープに格納 */
			request.setAttribute("searchlist", searchlist);
			request.setAttribute("searchCount", searchCount);

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

		} else if (submitType.equals("クリア")) {
			/* 検索結果を初期化 */
			ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
			request.setAttribute("searchlist", searchlist);
			int searchCount = 0;
			request.setAttribute("searchCount", searchCount);

			/* エラーメッセージを初期化 */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);
			int errorCount = 0;
			request.setAttribute("errorCount", errorCount);

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
		} else if (submitType.equals("新規")) {

			/* 検索画面で入力されていた値を取得 */
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

			/* 検索結果・エラーの情報を取得 */
			String errorCountS = request.getParameter("errorCount");
			String searchCountS = request.getParameter("searchCount");

			/* 検索画面の情報をセッションに格納(画面復元用) */
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
			session.setAttribute("addressS", addressS);
			session.setAttribute("addressdetailS", addressdetailS);
			session.setAttribute("errorCountS", errorCountS);
			session.setAttribute("searchCountS", searchCountS);

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
			request.setAttribute("readonly", "false");

			// フォワードの実行
			request.getRequestDispatcher("./Entry.jsp").forward(request, response);
		}else if(submitType.equals("変更")) {
			/* チェックボックスにて指定された登録IDをもとに更新画面へ遷移 */
			String[] selectedIdLists = request.getParameterValues("check");
			
			/* 検索画面で入力されていた値を取得 */
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

			/* 検索結果・エラーメッセージの個数を取得 */
			String errorCountS = request.getParameter("errorCount");
			String searchCountS = request.getParameter("searchCount");

			/* 検索画面の情報をセッションに格納(画面復元用) */
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
			session.setAttribute("addressS", addressS);
			session.setAttribute("addressdetailS", addressdetailS);
			session.setAttribute("errorCountS", errorCountS);
			session.setAttribute("searchCountS", searchCountS);
			
			/*チェックされている行が無い、または複数の場合、エラーメッセージを表示*/
			if(selectedIdLists == null || selectedIdLists.length > 1) {
				ArrayList<String> errorMessages = new ArrayList<String>();
				if(selectedIdLists == null) {
					errorMessages.add("対象データが選択されていません。\n住所リストにて対象を選択してください。");
				}else if(selectedIdLists.length > 1) {
					errorMessages.add("対象データが複数行選択されています。\n住所リストにて対象を1行のみ選択してください。");
				}
				
				request.setAttribute("errorMessages", errorMessages);
				int errorCount = errorMessages.size();
				request.setAttribute("errorCount", errorCount);
				
				if (!searchCountS.equals("0")) {
					ArgumentBean ab = new ArgumentBean();

					ab.setIdfrom(idfromS);
					ab.setIdto(idtoS);
					ab.setName(nameS);
					ab.setAgefrom(agefromS);
					ab.setAgeto(agetoS);
					ab.setSex(sexS);
					ab.setJob(jobS);
					ab.setTell(tellS);
					ab.setZip(zipS);
					ab.setAddress(addressS);
					ab.setAddressdetail(addressdetailS);

					/* データベースに対して検索処理を実施 */
					SearchDao sdao = new SearchDao();

					/* 検索結果を取得 */
					ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

					/* 検索結果の件数を取得 */
					int searchCount = searchlist.size();

					/* 検索結果をリクエストスコープに格納 */
					request.setAttribute("searchlist", searchlist);
					request.setAttribute("searchCount", searchCount);
				}else {
					ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
					request.setAttribute("searchlist", searchlist);
					int searchCount = 0;
					request.setAttribute("searchCount", searchCount);
				}

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

				/* 職業リストを再表示 */
				JobDao jdao = new JobDao();
				ArrayList<JobBean> joblist = jdao.selectJob();
				request.setAttribute("joblist", joblist);

				/* 検索画面へ遷移 */
				request.getRequestDispatcher("./Search.jsp").forward(request, response);
			}else {

				String selectedId = null;
				for(String id : selectedIdLists) {
					selectedId = id;
				}
				/* 入力値を格納するインスタンス */
				InsertBean ib = new InsertBean();

				/* フォーム内で入力された値を登録値としてセットする */
				ib.setId(selectedId);
				
				/* 登録IDが重複しているデータがないか検索 */
				SearchDao01 sdao01 = new SearchDao01();

				/* 検索結果を取得 */
				ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);
				
				/* 検索値を取得 */
				String name = searchlist01.get(0).getName();
				String age = String.valueOf(searchlist01.get(0).getAge());
				String sex = searchlist01.get(0).getSex();
				String job = searchlist01.get(0).getJob();
				String tell = searchlist01.get(0).getTell();
				String zip = searchlist01.get(0).getZip();
				String address = searchlist01.get(0).getAddress();
				String addressdetail = searchlist01.get(0).getAddressDetail();
				
				/* 入力フォームに検索した値をセット */
				request.setAttribute("id", selectedId);
				request.setAttribute("name", name);
				request.setAttribute("age", age);
				request.setAttribute("sex", sex);
				request.setAttribute("job", job);
				request.setAttribute("tell", tell);
				request.setAttribute("zip", zip);
				request.setAttribute("address", address);
				request.setAttribute("addressdetail", addressdetail);
				/*更新モード切替えのため、登録IDの入力欄を編集不可とする*/
				request.setAttribute("readonly", "true");
				
				/* エラーメッセージを格納するリスト */
				ArrayList<String> errorMessages = new ArrayList<String>();
				request.setAttribute("errorMessages", errorMessages);

				/* 職業リストを職業マスタから生成 */
				JobDao jdao = new JobDao();
				ArrayList<JobBean> joblist = jdao.selectJob();
				request.setAttribute("joblist", joblist);

				// フォワードの実行
				request.getRequestDispatcher("./Entry.jsp").forward(request, response);
				
			}
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
