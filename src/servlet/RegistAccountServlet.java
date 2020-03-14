package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.GetAccountListLogic;
import model.PostRegistAccountLogic;
import model.RegistAccount;

/**
 * Servlet implementation class RegistAccountServlet
 */
@WebServlet("/RegistAccountServlet")
public class RegistAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// アカウント登録画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションが残っている場合は破棄
		HttpSession session = request.getSession();
		if (session != null) {
		    session.invalidate();
		}
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String name = request.getParameter("userName");
		String password = request.getParameter("pass");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");

		if (userId == "" || name == "" || password == ""|| birthday == "" || gender == "") {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザーIDが重複しています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registAccount.jsp");
			dispatcher.forward(request, response);
		}

		// ユーザー情報を個人情報DBに追加
		RegistAccount registAccount = new RegistAccount(userId, name, password, birthday, gender);
		PostRegistAccountLogic postRegistAccountLogic = new PostRegistAccountLogic();
		postRegistAccountLogic.execute(registAccount);

		HttpSession newSession = request.getSession();
		// アカウントを取得して、セッションスコープに保存
		Account account = new Account(userId);
		GetAccountListLogic getAccountListLogic = new GetAccountListLogic();
		List<Account> accountList = getAccountListLogic.execute(account);
		newSession.setAttribute("accountList", accountList);

		// TOP画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request,response);

	}

}
