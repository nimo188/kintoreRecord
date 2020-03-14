package model;

import java.util.List;

import dao.KintoreMenuDAO;

public class GetKintoreTypeLogic {
	public List<KintoreType> execute(KintoreType kintoreType) {
		KintoreMenuDAO dao = new KintoreMenuDAO();
		List<KintoreType> kintoreList = dao.findAll(kintoreType);
		return kintoreList;
	}

}
