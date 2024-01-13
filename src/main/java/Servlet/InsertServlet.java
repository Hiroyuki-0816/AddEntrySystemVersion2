package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.InsertBean;
import Bean.InsertSearchBean;
import Bean.JobBean;
import Bean.SearchBean;
import Dao.InsertDao;
import Dao.JobDao;
import Dao.SearchDao01;

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

		// エラーチェック
		ArrayList<String> errorMessages = new ArrayList<String>();
		String msg = "は必ず入力してください。";
		String msg2 = "に誤りがあります。入力した文字数が多すぎます。";
		String msg3 = "に誤りがあります。入力した文字の型が違います。";

		if (id == "") {
			errorMessages.add("登録ID" + msg);
		} else if (id.length() > 8) {
			errorMessages.add("登録ID" + msg2);
		} else if (!id.matches("^[0-9]+$")) {
			errorMessages.add("登録ID" + msg3);
		}

		if (name == "") {
			errorMessages.add("氏名" + msg);
		} else if (name.length() > 20) {
			errorMessages.add("氏名" + msg2);
		}

		if (age == "") {
			errorMessages.add("年齢" + msg);
		} else if (age.length() > 3) {
			errorMessages.add("年齢" + msg2);
		} else if (!age.matches("^[0-9]+$")) {
			errorMessages.add("年齢" + msg3);
		}

		if (tell == "") {
			errorMessages.add("電話番号" + msg);
		} else if (tell.length() > 13) {
			errorMessages.add("電話番号" + msg2);
		} else if (!tell.matches("^[-0-9]+$")) {
			errorMessages.add("電話番号" + msg3);
		} else if (tell.startsWith("-") || tell.endsWith("-")) {
			errorMessages.add("ハイフン'-'で開始、または終了する文字列の登録はできません。");
		}

		if (zip == "") {
			errorMessages.add("郵便番号" + msg);
		} else if (zip.length() > 8) {
			errorMessages.add("郵便番号" + msg2);
		} else if (!zip.matches("^[-0-9]+$")) {
			errorMessages.add("郵便番号" + msg3);
		} else if (!zip.matches("[0-9]{3}-[0-9]{4}")) {
			errorMessages.add("郵便番号は書式：「999-9999」でなければ登録できません。");
		}

		if (address == "") {
			errorMessages.add("住所" + msg);
		} else if (address.length() > 20) {
			errorMessages.add("住所" + msg2);
		}

		if (addressdetail.length() > 20) {
			errorMessages.add("番地" + msg2);
		}

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

			// フォワードの実行
			request.getRequestDispatcher("./EntryError.jsp").forward(request, response);
		}

		if (errorMessages.size() == 0) {

			// 登録IDを格納するインスタンス
			InsertSearchBean isb = new InsertSearchBean();

			// フォーム内で入力された登録IDを検索値としてセットする
			isb.setId(id);

			/* 登録IDが重複しているデータがないか検索 */
			SearchDao01 sdao01 = new SearchDao01();

			/* 検索結果を取得 */
			ArrayList<SearchBean> searchlist = sdao01.insertSearch(isb);

//			/*確認用ポップアップ*/
//			JFrame frame = new JFrame();
//			
//			/* 確認用メッセージ */
//			String confirmI = "登録しますか？";
//			String confirmU = "入力された登録IDは既に登録されているものです。\n現在の入力内容で上書きしますか？";
			/* 登録処理を実施するか更新処理を実施するか判断する */
			if (searchlist.size() == 0) {
//				int optionI = JOptionPane.showConfirmDialog(frame, confirmI, "登録確認", JOptionPane.OK_CANCEL_OPTION,
//						JOptionPane.QUESTION_MESSAGE);
//				if (optionI == JOptionPane.OK_OPTION) {
//					System.out.println("登録しました。");
//				}
				// 入力値を格納するインスタンス
				InsertBean ib = new InsertBean();

				// フォーム内で入力された値を登録値としてセットする
				ib.setId(id);
				ib.setName(name);
				ib.setAge(age);
				ib.setSex(sex);
				ib.setJob(job);
				ib.setTell(tell);
				ib.setZip(zip);
				ib.setAddress(address);
				ib.setAddressDetail(addressdetail);

				/* データベースに対して検索処理を実施 */
				InsertDao idao = new InsertDao();
				idao.insert(ib);
				
				/* 職業リストを再表示 */
				JobDao jdao = new JobDao();
				ArrayList<JobBean> joblist = jdao.selectJob();
				request.setAttribute("joblist", joblist);

				// フォワードの実行
				request.getRequestDispatcher("./Search.jsp").forward(request, response);
			} else {
//				int optionU = JOptionPane.showConfirmDialog(frame, confirmU, "登録確認", JOptionPane.OK_CANCEL_OPTION,
//						JOptionPane.QUESTION_MESSAGE);
//				if (optionU == JOptionPane.OK_OPTION) {
//					System.out.println("上書きしました。");
//				}
			}
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
