package model;

import dao.ExRecordDAO;

public class PostExRecordLogic {
	public void exeute(Regist regist) {
		ExRecordDAO dao = new ExRecordDAO();
		dao.create(regist);
	}


}
