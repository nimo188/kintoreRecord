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


/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				// ログインしているか確認するため
				// セッションスコープからユーザー情報を取得
				HttpSession session = request.getSession();
				List<Account> accountList = (List<Account>) session.getAttribute("accountList");

				if(accountList == null) {
					response.sendRedirect("/kintoreRecord/");
				} else { // ログイン済みの場合
					// フォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
					dispatcher.forward(request, response);
				}
	}

}
