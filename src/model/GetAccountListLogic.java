package model;

import java.util.List;

import dao.AccountDAO;

public class GetAccountListLogic {

	public List<Account> execute(Account account) {
		AccountDAO dao = new AccountDAO();
		List<Account> accountList = dao.findAccountList(account);
		return accountList;
	}
}
