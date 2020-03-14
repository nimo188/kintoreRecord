package model;

import java.util.List;

import dao.ExRecordDAO;

public class GetRecordListLogic {
	public List<RecordList> execute(Account account) {
		ExRecordDAO dao = new ExRecordDAO();
		List<RecordList> recordList = dao.findAll(account);
		return recordList;
	}

}
