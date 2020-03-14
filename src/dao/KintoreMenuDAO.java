package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KintoreType;




public class KintoreMenuDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mariadb://localhost/muscle_record";
	private final String DB_USER = "root";
	private final String DB_PASS = "sa5su6ke7";

	public List<KintoreType> findAll(KintoreType kintoreType) {
		List<KintoreType> kintoreList = new ArrayList<>();

		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS)) {
		// SELECT文の準備
		String sql = "SELECT parts,type FROM KintoreMaster WHERE parts = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,kintoreType.getParts());

		// SELECTを実行
		ResultSet rs = pStmt.executeQuery();

		// SELECT文の結果をArrayListに格納
		while(rs.next()) {

			String type = rs.getString("type");
			String parts = rs.getString("parts");
			KintoreType Type = new KintoreType(parts, type);
			kintoreList.add(Type);
		}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return kintoreList;
	}

}
