package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import model.GetUserIdCheckLogic;
import model.PostRegistAccountLogic;
import model.RegistAccount;

/**
 * Servlet implementation class RegistAccountServlet
 */
@WebServlet("/RegistAccountServlet")
public class RegistAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** パスワードを安全にするためのアルゴリズム */
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    /** ストレッチング回数 */
    private static final int ITERATION_COUNT = 10000;
    /** 生成される鍵の長さ */
    private static final int KEY_LENGTH = 50;

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

		Account account = new Account(userId);
		GetUserIdCheckLogic bo = new GetUserIdCheckLogic();
		boolean result = bo.execute(account);

		if (result) {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザーIDが重複しています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registAccount.jsp");
			dispatcher.forward(request, response);
		} else if (!checkUserId(userId)){
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザーIDは半角英数字10文字までです。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registAccount.jsp");
			dispatcher.forward(request, response);
		} else if (!checkName(name)) {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "名前は全角10文字までです。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registAccount.jsp");
			dispatcher.forward(request, response);
		} else if (!checkBirthday(birthday)) {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "生年月日を正しく入力して下さい");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registAccount.jsp");
			dispatcher.forward(request, response);
		} else {
			// 平文のパスワードを安全なパスワードへ変換する
			String safetyPass = getSafetyPassword(password, userId);

			// ユーザー情報を個人情報DBに追加
			RegistAccount registAccount = new RegistAccount(userId, name, safetyPass, birthday, gender);
			PostRegistAccountLogic postRegistAccountLogic = new PostRegistAccountLogic();
			postRegistAccountLogic.execute(registAccount);

			HttpSession newSession = request.getSession();
			// アカウントを取得して、セッションスコープに保存
			GetAccountListLogic getAccountListLogic = new GetAccountListLogic();
			List<Account> accountList = getAccountListLogic.execute(account);
			newSession.setAttribute("accountList", accountList);

			// TOP画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
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

    /**
     * ユーザーIDを入力チェックします
     * @param userId
     * @return 入力が正しいか
     */
    private static boolean checkUserId (String userId) {
    	// 正規表現のパターンを作成
        Pattern p = Pattern.compile("^[0-9a-zA-Z]{1,10}$");
        Matcher m = p.matcher(userId);
        return m.find();
    }
    /**
     * 名前を入力チェックします
     * @param name
     * @return 入力が正しいか
     */
    private static boolean checkName (String name) {
    	// 正規表現のパターンを作成
        Pattern p = Pattern.compile("^[^ -~｡-ﾟ\t]{1,10}$");
        Matcher m = p.matcher(name);
        return m.find();
    }

    /**
     * 生年月日を入力チェックします
     * @param birthday
     * @return 入力が正しいか
     */
    private static boolean checkBirthday (String birthday) {
    	// 正規表現のパターンを作成
        Pattern p = Pattern.compile("^[0-9]{8}$");
        Matcher m = p.matcher(birthday);
        return m.find();
    }

}
