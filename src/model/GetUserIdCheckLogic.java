package model;

import dao.AccountDAO;

public class GetUserIdCheckLogic {
	public boolean execute (Account account)  {
		AccountDAO dao = new AccountDAO();
		Account checkUserId = dao.checkByUserId(account);
		return checkUserId != null;
	}

}
