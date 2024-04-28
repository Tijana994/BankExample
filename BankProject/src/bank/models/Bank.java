package bank.models;

import bank.managers.*;

public class Bank {
	
	private UserManager userManager;
	private DocumentManager documentManager;
	private ComplaintManager complaintManager;
	private AccountManager accountManager;
	private NotificationManager notificationManager;
	
	public Bank()
	{
		this.userManager = new UserManager();
		this.documentManager = new DocumentManager();
		this.complaintManager = new ComplaintManager();
		this.accountManager = new AccountManager();
		this.notificationManager = new NotificationManager();
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

	public NotificationManager getNotificationManager() {
		return notificationManager;
	}
}
