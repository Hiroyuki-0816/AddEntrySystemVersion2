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
import Dao.InsertDao;
import Dao.JobDao;
import Dao.SearchDao;
import Dao.SearchDao01;
import Dao.UpdateDao;
import Validation.InsertValidation;
import Validation.SearchValidation;

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

		/* ボタンの値で処理を分岐 */
		String submitType = request.getParameter("button");

		if (Objects.equals(submitType, "登録")) {
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

				/* 登録IDの入力欄の編集の可否を制御 */
				HttpSession session = request.getSession();
				String submitTypeS = (String) session.getAttribute("submitTypeS");
				if (Objects.equals(submitTypeS, "変更")) {
					request.setAttribute("readonly", "update");
				} else {
					request.setAttribute("readonly", "insert");
				}

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
			} else if (errorMessages.size() == 0) {

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

				/* セッション情報を取得 */
				HttpSession session = request.getSession();
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
				String errorCountS = (String) session.getAttribute("errorCountS");
				String searchCountS = (String) session.getAttribute("searchCountS");

				/* 検索結果が表示されている場合のみ再検索 */
				if (errorCountS.equals("0") && searchCountS.equals("0")) {

					ArrayList<SearchBean> searchlistB = new ArrayList<SearchBean>();
					request.setAttribute("searchlist", searchlistB);
					int searchCountB = 0;
					request.setAttribute("searchCount", searchCountB);

					ArrayList<String> errorMessagesB = new ArrayList<String>();
					request.setAttribute("errorMessages", errorMessagesB);
					int errorCountB = 0;
					request.setAttribute("errorCount", errorCountB);

				} else {
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

					/* エラーチェックを実施 */
					SearchValidation svalidate = new SearchValidation();

					/* エラーチェックの結果を取得 */
					ArrayList<String> errorMessagesB = svalidate.errorCheckS(ab);

					/* エラーメッセージの数を取得 */
					int errorCountB = errorMessagesB.size();

					/* データベースに対して検索処理を実施 */
					SearchDao sdao = new SearchDao();

					/* 検索結果を取得 */
					ArrayList<SearchBean> searchlistB = sdao.selectSearch(ab);

					/* 検索結果の件数を取得 */
					int searchCountB = searchlistB.size();

					/* 検索結果が0件の場合に表示 */
					if (errorCountB == 0 && searchCountB == 0) {
						errorMessagesB.add("該当するデータが存在しません。");
					}
					/* エラーメッセージをリクエストスコープに格納 */
					request.setAttribute("errorMessages", errorMessagesB);
					request.setAttribute("errorCount", errorCountB);

					/* 検索結果をリクエストスコープに格納 */
					request.setAttribute("searchlist", searchlistB);
					request.setAttribute("searchCount", searchCountB);
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

				/* 検索画面へ遷移 */
				request.getRequestDispatcher("./Search.jsp").forward(request, response);

			}
		} else if (Objects.equals(submitType, "クリア")) {
			/* エラーメッセージを初期化 */
			ArrayList<String> errorMessages = new ArrayList<String>();
			request.setAttribute("errorMessages", errorMessages);

			/* 職業リストを初期化 */
			JobDao jdao = new JobDao();
			ArrayList<JobBean> joblist = jdao.selectJob();
			request.setAttribute("joblist", joblist);

			/* 更新モードの場合、登録IDの入力値は初期化しない */
			HttpSession session = request.getSession();
			String submitTypeS = (String) session.getAttribute("submitTypeS");
			if (Objects.equals(submitTypeS, "変更")) {
				request.setAttribute("readonly", "update");
				String id = request.getParameter("id");
				request.setAttribute("id", id);
			} else {
				request.setAttribute("readonly", "insert");
				request.setAttribute("id", "");
			}

			/* 入力フォームを初期化 */
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
		} else if (Objects.equals(submitType, "中止")) {

			/* セッション情報を取得 */
			HttpSession session = request.getSession();
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
			String errorCountS = (String) session.getAttribute("errorCountS");
			String searchCountS = (String) session.getAttribute("searchCountS");

			/* 検索結果が表示されている場合のみ再検索 */
			if (errorCountS.equals("0") && searchCountS.equals("0")) {

				ArrayList<SearchBean> searchlistB = new ArrayList<SearchBean>();
				request.setAttribute("searchlist", searchlistB);
				int searchCountB = 0;
				request.setAttribute("searchCount", searchCountB);

				ArrayList<String> errorMessagesB = new ArrayList<String>();
				request.setAttribute("errorMessages", errorMessagesB);
				int errorCountB = 0;
				request.setAttribute("errorCount", errorCountB);

			} else {
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

				/* エラーチェックを実施 */
				SearchValidation svalidate = new SearchValidation();

				/* エラーチェックの結果を取得 */
				ArrayList<String> errorMessagesB = svalidate.errorCheckS(ab);

				/* エラーメッセージの数を取得 */
				int errorCountB = errorMessagesB.size();

				/* データベースに対して検索処理を実施 */
				SearchDao sdao = new SearchDao();

				/* 検索結果を取得 */
				ArrayList<SearchBean> searchlistB = sdao.selectSearch(ab);

				/* 検索結果の件数を取得 */
				int searchCountB = searchlistB.size();

				/* 検索結果が0件の場合に表示 */
				if (errorCountB == 0 && searchCountB == 0) {
					errorMessagesB.add("該当するデータが存在しません。");
				}
				/* エラーメッセージをリクエストスコープに格納 */
				request.setAttribute("errorMessages", errorMessagesB);
				request.setAttribute("errorCount", errorCountB);

				/* 検索結果をリクエストスコープに格納 */
				request.setAttribute("searchlist", searchlistB);
				request.setAttribute("searchCount", searchCountB);
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
