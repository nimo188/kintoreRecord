package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Account;
import model.RecordList;
import model.Regist;



public class ExRecordDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mariadb://localhost/muscle_record";
	private final String DB_USER = "root";
	private final String DB_PASS = "sa5su6ke7";

	public boolean create (Regist regist) {
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS)) {

			// INSERT文の準備（idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO ExRecord(userId,parts,type,rep,exSet,weight,createName) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERT文中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, regist.getUserId());
			pStmt.setString(2, regist.getParts());
			pStmt.setString(3, regist.getType());
			pStmt.setString(4, regist.getRep());
			pStmt.setString(5, regist.getExSet());
			pStmt.setString(6, regist.getWeight());
			pStmt.setString(7, regist.getName());

			// INSERT文を実行（resultには追加）
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<RecordList> findAll(Account account) {
		List<RecordList> recordList = new ArrayList<>();

		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS)) {
		// SELECT文の準備
		String sql = "SELECT parts,type, rep, exSet, weight, createDate FROM ExRecord WHERE userId = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,account.getUserId());

		// SELECTを実行
		ResultSet rs = pStmt.executeQuery();

		// SELECT文の結果をArrayListに格納
		while(rs.next()) {

			String parts = rs.getString("parts");
			String type = rs.getString("type");
			String rep = rs.getString("rep");
			String exSet = rs.getString("exSet");
			String weight = rs.getString("weight");
			Date createDate = rs.getDate("createDate");
			RecordList record = new RecordList(parts, type, rep, exSet, weight, createDate);
			recordList.add(record);
		}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return recordList;
	}

}
