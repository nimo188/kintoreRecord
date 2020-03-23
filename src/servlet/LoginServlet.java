package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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
	/** パスワードを安全にするためのアルゴリズム */
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    /** ストレッチング回数 */
    private static final int ITERATION_COUNT = 10000;
    /** 生成される鍵の長さ */
    private static final int KEY_LENGTH = 50;

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

		// ハッシュ化したパスワードを取得
		String safetyPass = getSafetyPassword(pass, userId);

		// ログイン処理の実行
		Login login = new Login(userId,safetyPass);
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

    /**
     *　平文のパスワードとソルトから安全なパスワードを生成し、返却します
     *
     * @param password 平文のパスワード
     * @param salt ソルト
     * @return 安全なパスワード
     */
    public static String getSafetyPassword(String password, String salt) {

        char[] passCharAry = password.toCharArray();
        byte[] hashedSalt = getHashedSalt(salt);

        PBEKeySpec keySpec = new PBEKeySpec(passCharAry, hashedSalt, ITERATION_COUNT, KEY_LENGTH);

        SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        SecretKey secretKey;
        try {
            secretKey = skf.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        byte[] passByteAry = secretKey.getEncoded();

        // 生成されたバイト配列を16進数の文字列に変換
        StringBuilder sb = new StringBuilder(64);
        for (byte b : passByteAry) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    /**
     * ソルトをハッシュ化して返却します
     * ※ハッシュアルゴリズムはSHA-256を使用
     *
     * @param salt ソルト
     * @return ハッシュ化されたバイト配列のソルト
     */
    private static byte[] getHashedSalt(String salt) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(salt.getBytes());
        return messageDigest.digest();
    }

}
