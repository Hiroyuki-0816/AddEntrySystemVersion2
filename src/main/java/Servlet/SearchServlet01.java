package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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

		/* リンクから遷移した場合 */
		String submitId = request.getParameter("submitId");
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
		/* 検索結果・エラーの情報を取得 */
		String errorCount = request.getParameter("errorCount");
		String searchCount = request.getParameter("searchCount");

		if (Objects.equals(submitType, "検索")) {

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
			int errorCountS = errorMessages.size();

			/* データベースに対して検索処理を実施 */
			SearchDao sdao = new SearchDao();

			/* 検索結果を取得 */
			ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

			/* 検索結果の件数を取得 */
			int searchCountS = searchlist.size();

			/* 検索結果をリクエストスコープに格納 */
			request.setAttribute("searchlist", searchlist);
			request.setAttribute("searchCount", searchCountS);

			/* 検索結果が0件の場合に表示 */
			if (errorCountS == 0 && searchCountS == 0) {
				errorMessages.add("該当するデータが存在しません。");
			}

			/* エラーメッセージをリクエストスコープに格納 */
			request.setAttribute("errorMessages", errorMessages);
			request.setAttribute("errorCount", errorCountS);

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

		} else if (Objects.equals(submitType, "クリア")) {
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
			request.setAttribute("searchCount", 0);
			request.setAttribute("errorCount", 0);

			/* 職業リストを再表示 */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			/* フォワードの実行 */
			request.getRequestDispatcher("./Search.jsp").forward(request, response);
		} else if (Objects.equals(submitType, "新規")) {
			/* 検索画面の情報をセッションに格納(画面復元用) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfrom);
			session.setAttribute("idtoS", idto);
			session.setAttribute("nameS", name);
			session.setAttribute("agefromS", agefrom);
			session.setAttribute("agetoS", ageto);
			session.setAttribute("sexS", sex);
			session.setAttribute("jobS", job);
			session.setAttribute("tellS", tell);
			session.setAttribute("zipS", zip);
			session.setAttribute("addressS", address);
			session.setAttribute("addressdetailS", addressdetail);
			session.setAttribute("errorCountS", errorCount);
			session.setAttribute("searchCountS", searchCount);
			session.setAttribute("submitTypeS", submitType);

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
			/* 新規モードなので、登録IDの編集を可とする */
			request.setAttribute("readonly", "insert");

			// フォワードの実行
			request.getRequestDispatcher("./Entry.jsp").forward(request, response);
		} else if (Objects.equals(submitType, "変更")) {

			/* 検索画面の情報をセッションに格納(画面復元用) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfrom);
			session.setAttribute("idtoS", idto);
			session.setAttribute("nameS", name);
			session.setAttribute("agefromS", agefrom);
			session.setAttribute("agetoS", ageto);
			session.setAttribute("sexS", sex);
			session.setAttribute("jobS", job);
			session.setAttribute("tellS", tell);
			session.setAttribute("zipS", zip);
			session.setAttribute("addressS", address);
			session.setAttribute("addressdetailS", addressdetail);
			session.setAttribute("errorCountS", errorCount);
			session.setAttribute("searchCountS", searchCount);
			session.setAttribute("submitTypeS", submitType);

			/* チェックボックスにて指定された登録IDをもとに更新画面へ遷移 */
			String[] selectedIdLists = request.getParameterValues("check");

			/* チェックされている行が無い、または複数の場合、エラーメッセージを表示 */
			if (selectedIdLists == null || selectedIdLists.length > 1) {
				ArrayList<String> errorMessages = new ArrayList<String>();
				if (selectedIdLists == null) {
					errorMessages.add("対象データが選択されていません。\n住所リストにて対象を選択してください。");
				} else if (selectedIdLists.length > 1) {
					errorMessages.add("対象データが複数行選択されています。\n住所リストにて対象を1行のみ選択してください。");
				}

				request.setAttribute("errorMessages", errorMessages);
				request.setAttribute("errorCount", 1);

				if (!searchCount.equals("0")) {
					ArgumentBean ab = new ArgumentBean();

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

					/* データベースに対して検索処理を実施 */
					SearchDao sdao = new SearchDao();

					/* 検索結果を取得 */
					ArrayList<SearchBean> searchlist = sdao.selectSearch(ab);

					/* 検索結果をリクエストスコープに格納 */
					request.setAttribute("searchlist", searchlist);
					request.setAttribute("searchCount", searchCount);
				} else {
					ArrayList<SearchBean> searchlist = new ArrayList<SearchBean>();
					request.setAttribute("searchlist", searchlist);
					request.setAttribute("searchCount", 0);
				}

				/* 検索画面を復元 */
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

				/* 検索画面へ遷移 */
				request.getRequestDispatcher("./Search.jsp").forward(request, response);
			} else {

				String selectedId = null;
				for (String id : selectedIdLists) {
					selectedId = id;
				}
				/* 入力値を格納するインスタンス */
				InsertBean ib = new InsertBean();

				/* フォーム内で入力された値を登録値としてセットする */
				ib.setId(selectedId);

				/* 登録IDを引数としてテーブルより検索 */
				SearchDao01 sdao01 = new SearchDao01();

				/* 検索結果を取得 */
				ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);

				/* 検索値を取得 */
				String nameE = searchlist01.get(0).getName();
				String ageE = String.valueOf(searchlist01.get(0).getAge());
				String sexE = searchlist01.get(0).getSex();
				String jobE = searchlist01.get(0).getJob();
				String tellE = searchlist01.get(0).getTell();
				String zipE = searchlist01.get(0).getZip();
				String addressE = searchlist01.get(0).getAddress();
				String addressdetailE = searchlist01.get(0).getAddressDetail();

				/* 入力フォームに検索した値をセット */
				request.setAttribute("id", selectedId);
				request.setAttribute("name", nameE);
				request.setAttribute("age", ageE);
				request.setAttribute("sex", sexE);
				request.setAttribute("job", jobE);
				request.setAttribute("tell", tellE);
				request.setAttribute("zip", zipE);
				request.setAttribute("address", addressE);
				request.setAttribute("addressdetail", addressdetailE);
				/* 変更モードなので、登録IDの編集を不可とする */
				request.setAttribute("readonly", "update");

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
		} else if (submitId != null) {

			/* 検索画面の情報をセッションに格納(画面復元用) */
			HttpSession session = request.getSession();
			session.setAttribute("idfromS", idfrom);
			session.setAttribute("idtoS", idto);
			session.setAttribute("nameS", name);
			session.setAttribute("agefromS", agefrom);
			session.setAttribute("agetoS", ageto);
			session.setAttribute("sexS", sex);
			session.setAttribute("jobS", job);
			session.setAttribute("tellS", tell);
			session.setAttribute("zipS", zip);
			session.setAttribute("addressS", address);
			session.setAttribute("addressdetailS", addressdetail);
			session.setAttribute("errorCountS", errorCount);
			session.setAttribute("searchCountS", searchCount);
			session.setAttribute("submitTypeS", "変更");

			/* 入力値を格納するインスタンス */
			InsertBean ib = new InsertBean();

			/* リンクのIDを登録値としてセットする */
			ib.setId(submitId);

			/* 登録IDを引数としてテーブルより検索 */
			SearchDao01 sdao01 = new SearchDao01();

			/* 検索結果を取得 */
			ArrayList<SearchBean> searchlist01 = sdao01.insertSearch(ib);

			/* 検索値を取得 */
			String nameE = searchlist01.get(0).getName();
			String ageE = String.valueOf(searchlist01.get(0).getAge());
			String sexE = searchlist01.get(0).getSex();
			String jobE = searchlist01.get(0).getJob();
			String tellE = searchlist01.get(0).getTell();
			String zipE = searchlist01.get(0).getZip();
			String addressE = searchlist01.get(0).getAddress();
			String addressdetailE = searchlist01.get(0).getAddressDetail();

			/* 入力フォームに検索した値をセット */
			request.setAttribute("id", submitId);
			request.setAttribute("name", nameE);
			request.setAttribute("age", ageE);
			request.setAttribute("sex", sexE);
			request.setAttribute("job", jobE);
			request.setAttribute("tell", tellE);
			request.setAttribute("zip", zipE);
			request.setAttribute("address", addressE);
			request.setAttribute("addressdetail", addressdetailE);
			/* 変更モードなので、登録IDの編集を不可とする */
			request.setAttribute("readonly", "update");

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
