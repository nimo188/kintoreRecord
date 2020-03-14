package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.RegistAccount;

public class RegistAccountDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mariadb://localhost/muscle_record";
	private final String DB_USER = "root";
	private final String DB_PASS = "sa5su6ke7";

	public boolean create (RegistAccount registAccount) {
		// データベース接続
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// INSERT文の準備（idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO personalInfomation(userId,name,password,birthday,gender,createName,updateName) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, registAccount.getUserId());
			pStmt.setString(2, registAccount.getName());
			pStmt.setString(3, registAccount.getPassword());
			pStmt.setString(4, registAccount.getBirthday());
			pStmt.setString(5, registAccount.getGender());
			pStmt.setString(6, registAccount.getName());
			pStmt.setString(7, registAccount.getName());

			// INSERT文を実行（resultには追加）
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
