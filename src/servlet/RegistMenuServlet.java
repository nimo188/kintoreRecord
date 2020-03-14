package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetKintoreTypeLogic;
import model.KintoreType;

/**
 * Servlet implementation class RegistMenuServlet
 */
@WebServlet("/RegistMenuServlet")
public class RegistMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String parts = request.getParameter("parts");
		// 筋トレ種目リストを取得して、リクエストスコープに保存
		KintoreType kintoreType = new KintoreType(parts);
		GetKintoreTypeLogic getKintoreTypeLogic = new GetKintoreTypeLogic();
		List<KintoreType> kintoreList = getKintoreTypeLogic.execute(kintoreType);
		request.setAttribute("kintoreList", kintoreList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registTypeSelect.jsp");
		dispatcher.forward(request, response);
	}

}
