package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Login;

public class AccountDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mariadb://localhost/muscle_record";
	private final String DB_USER = "root";
	private final String DB_PASS = "sa5su6ke7";

	// ログイン情報を取得する
	public Account findByLogin(Login login) {
		Account account = null;

	// データベースへ接続
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		// SELECT文を準備
		String sql = "SELECT userId,name FROM personalInfomation WHERE userId = ? AND password = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,login.getUserId());
		pStmt.setString(2, login.getPass());

		// SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();

		// 一致したユーザーが存在した場合
		// そのユーザーを表すAccountインスタンスを生成
		if(rs.next()) {
			// 結果表からデータを取得
			String userId = rs.getString("userId");
			String name = rs.getString("name");
			account = new Account(userId, name);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		// 見つかったユーザーまたはnullを返す
		return account;
		}

	// アカウントリストを取得する
	public List<Account> findAccountList(Account account) {
		List<Account> accountList = new ArrayList<>();

		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS)) {
			// SELECT文の準備
			String sql = "SELECT userId, name FROM personalInfomation WHERE userId = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
			while(rs.next()) {
				String userId = rs.getString("userId");
				String name = rs.getString("name");
				Account accountUser = new Account(userId, name);
				accountList.add(accountUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
		return accountList;
		}

	// 重複したユーザーIDがあるかどうかチェックする
	public Account checkByUserId(Account account) {
		Account checkUserId = null;

	// データベースへ接続
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		// SELECT文を準備
		String sql = "SELECT userId FROM personalInfomation WHERE userId = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,account.getUserId());

		// SELECT文を実行し、結果表を取得
		ResultSet rs = pStmt.executeQuery();

		// 一致したユーザーIDが存在した場合
		// そのユーザーを表すAccountインスタンスを生成
		if(rs.next()) {
			// 結果表からデータを取得
			String userId = rs.getString("userId");
			checkUserId = new Account(userId);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		// 見つかったユーザーIDまたはnullを返す
		return checkUserId;
		}

}
