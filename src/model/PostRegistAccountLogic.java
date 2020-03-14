package model;

import dao.RegistAccountDAO;

public class PostRegistAccountLogic {

	public void execute(RegistAccount registAccount) {
		RegistAccountDAO dao = new RegistAccountDAO();
		dao.create(registAccount);
	}
}
