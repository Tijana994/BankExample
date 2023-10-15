package bank;

import bank.models.*;

public class BankProject {

	private Bank bank;
	
	public BankProject()
	{
		bank = new Bank();
	}
	
	public static void main(String[] args) {
		BankProject bankProject = new BankProject();
		/*bankProject.bank.createCity("Novi Sad");
		bankProject.bank.collectingDocuments(1);
		bankProject.bank.createUser("Tommica");*/
		var c = new Complaint();
		//c.CreateDenial();
		//c.setConsent(bankProject.bank.createConsentDocument("consent test"));
		c.CreateWithDraw();
		/*c.CreateWithDraw(new Withdraw());
		bankProject.bank.createComplaintOnData();*/
		System.out.println("End");
	}
}
