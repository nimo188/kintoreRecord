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
import model.Login;
import model.LoginLogic;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String userId = request.getParameter("id");
		String pass = request.getParameter("pass");

		// ユーザーIDもしくはパスワードが空の場合
		// 空であるエラーメッセージを表示
		if (userId == "" && pass == "") {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザーIDかパスワードが入力されていません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else if (userId == "") {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザーIDが入力されていません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else if (pass == "") {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "パスワードが入力されていません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

		// ログイン処理の実行
		Login login = new Login(userId,pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);

		// ログイン処理の成否によって処理を分岐
		if(result) { // ログイン成功時

			HttpSession newSession = request.getSession();
			// アカウントを取得して、セッションスコープに保存
			Account account = new Account(userId);
			GetAccountListLogic getAccountListLogic = new GetAccountListLogic();
			List<Account> accountList = getAccountListLogic.execute(account);
			newSession.setAttribute("accountList", accountList);

			// TOP画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request,response);

		} else { // ログイン失敗時
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザーIDかパスワードが間違っています。");

			// ログイン画面フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request,response);
		}

	}

}
