package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ArgumentBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.JobDao;
import Dao.SearchDao;

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

		// エラーチェック
		ArrayList<String> errorMessages = new ArrayList<String>();
		String msg = "に誤りがあります。入力した文字数が多すぎます。";
		String msg2 = "に誤りがあります。入力した文字の型が違います。";
		String msg3 = "の範囲指定に誤りがあります";

		if (idfrom.length() > 8) {
			errorMessages.add("登録IDFROM" + msg);
		} else if (idfrom != "" && !idfrom.matches("^[0-9]+$")) {
			errorMessages.add("登録IDFROM" + msg2);
		}

		if (idto.length() > 8) {
			errorMessages.add("登録IDTO" + msg);
		} else if (idto != "" && !idto.matches("^[0-9]+$")) {
			errorMessages.add("登録IDTO" + msg2);
		}

		if ((idfrom != "" && idto != "") && (idfrom.matches("^[0-9]+$") && idto.matches("^[0-9]+$"))
				&& Integer.parseInt(idfrom) > Integer.parseInt(idto)) {
			errorMessages.add("登録ID" + msg3);
		}

		if (name.length() > 20) {
			errorMessages.add("氏名" + msg);
		}

		if (agefrom.length() > 3) {
			errorMessages.add("年齢FROM" + msg);
		} else if (agefrom != "" && !agefrom.matches("^[0-9]+$")) {
			errorMessages.add("年齢FROM" + msg2);
		}

		if (ageto.length() > 3) {
			errorMessages.add("年齢TO" + msg);
		} else if (ageto != "" && !ageto.matches("^[0-9]+$")) {
			errorMessages.add("年齢TO" + msg2);
		}

		if ((agefrom != "" && ageto != "") && (agefrom.matches("^[0-9]+$") && ageto.matches("^[0-9]+$"))
				&& Integer.parseInt(agefrom) > Integer.parseInt(ageto)) {
			errorMessages.add("年齢" + msg3);
		}

		if (tell.length() > 13) {
			errorMessages.add("電話番号" + msg);
		} else if (tell != "" && !tell.matches("^[-0-9]+$")) {
			errorMessages.add("電話番号" + msg2);
		}

		if (zip.length() > 8) {
			errorMessages.add("郵便番号" + msg);
		} else if (zip != "" && !zip.matches("^[-0-9]+$")) {
			errorMessages.add("郵便番号" + msg2);
		}

		if (address.length() > 20) {
			errorMessages.add("住所" + msg);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("番地" + msg);
		}

			// 検索値を格納するインスタンス
			ArgumentBean ab = new ArgumentBean();

			// フォーム内で入力された値を検索値としてセットする
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

			/*検索結果が0件の場合に表示*/
			if (errorMessages.size() == 0 && searchlist.size() == 0) {
				errorMessages.add("該当するデータが存在しません。");
			}

			/* 検索結果をリクエストスコープに格納 */
			request.setAttribute("searchlist", searchlist);

		/* エラーメッセージをリクエストスコープに格納 */
		request.setAttribute("errorMessages", errorMessages);

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

		// フォワードの実行
		request.getRequestDispatcher("./Search01.jsp").forward(request, response);
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
