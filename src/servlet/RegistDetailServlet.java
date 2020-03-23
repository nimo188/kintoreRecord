package servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.PostExRecordLogic;
import model.Regist;

/**
 * Servlet implementation class RegistDetailServlet
 */
@WebServlet("/RegistDetailServlet")
public class RegistDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 筋トレの種目を取得する
		String parts = request.getParameter("parts");
		String type = request.getParameter("type");

		// 筋トレの種目をdoPostで値を渡すためにリクエストスコープに保存する
		request.setAttribute("parts", parts);
		request.setAttribute("type", type);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String rep = request.getParameter("rep");
		String exSet = request.getParameter("exSet");
		String weight = request.getParameter("weight");

		if (!checkRep(rep) || !checkExSet(exSet) || !checkWeight(weight) ) {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "いずれかの値が正しく入力できていません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registDetail.jsp");
			dispatcher.forward(request, response);
		} else {

		HttpSession session = request.getSession();
		// セッションスコープに保存されたユーザー情報を取得
		List<Account> accountList = (List<Account>) session.getAttribute("accountList");
		String userId = accountList.get(0).getUserId();
		String name = accountList.get(0).getName();

		// リクエストスコープに保存された筋トレ情報を取得
		String parts = request.getParameter("parts");
		String type = request.getParameter("type");


		// ユーザー、筋トレ情報を筋トレ管理DBに追加
		Regist regist = new Regist(userId, name, parts, type, rep, exSet, weight);
		PostExRecordLogic postExRecordLogic = new PostExRecordLogic();
		postExRecordLogic.exeute(regist);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registComplete.jsp");
		dispatcher.forward(request, response);
		}

	}

	/**
     * 値の入力チェックします
     * @param rep
     * @return 入力が正しいか
     */
    private static boolean checkRep (String rep) {
    	// 正規表現のパターンを作成
        Pattern p = Pattern.compile("^[0-9]{1,4}$");
        Matcher m = p.matcher(rep);
        return m.find();
    }

    /**
     * 値の入力チェックします
     * @param exSet
     * @return 入力が正しいか
     */
    private static boolean checkExSet (String exSet) {
    	// 正規表現のパターンを作成
        Pattern p = Pattern.compile("^[0-9]{1,4}$");
        Matcher m = p.matcher(exSet);
        return m.find();
    }

    /**
     * 値の入力チェックします
     * @param weight
     * @return 入力が正しいか
     */
    private static boolean checkWeight (String weight) {
    	// 正規表現のパターンを作成
        Pattern p = Pattern.compile("^[0-9]{1,4}$");
        Matcher m = p.matcher(weight);
        return m.find();
    }

}
