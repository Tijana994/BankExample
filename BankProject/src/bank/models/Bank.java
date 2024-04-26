package bank.models;

import bank.managers.*;

public class Bank {
	
	private UserManager userManager;
	private DocumentManager documentManager;
	private ComplaintManager complaintManager;
	private AccountManager accountManager;
	
	public Bank()
	{
		this.userManager = new UserManager();
		this.documentManager = new DocumentManager();
		this.complaintManager = new ComplaintManager();
		this.accountManager = new AccountManager();
	}
	
	public UserManager getUserManager() {
		return userManager;
	}

	public DocumentManager getDocumentManager() {
		return documentManager;
	}

	public ComplaintManager getComplaintManager() {
		return complaintManager;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}
}
